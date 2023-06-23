package bitedu.bipa.quiz.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import bitedu.bipa.exception.NotRentedBookException;
import bitedu.bipa.exception.RentFailedException;
import bitedu.bipa.exception.RentedBookException;
import bitedu.bipa.exception.ServiceStopException;
import bitedu.bipa.quiz.dao.LibraryDAO;
import bitedu.bipa.quiz.util.ConnectionManager;
import bitedu.bipa.quiz.util.DateCalculation;
import bitedu.bipa.quiz.vo.BookUseStatusVO;
import bitedu.bipa.quiz.vo.BookVO;
import bitedu.bipa.quiz.vo.UserBookStatusVO;
import bitedu.bipa.quiz.vo.UserVO;

public class LibraryBookService {
	private ConnectionManager manager;
	
	private LibraryDAO dao; 
	
	public LibraryBookService() {
		dao = new LibraryDAO();
		this.manager = ConnectionManager.getInstance();
	}
	
	//select * from book_use_status where user_id = 'user1' and borrow_start between '2023-6-1' and '2023-6-30';
	// select * from book_user
	 
	// 이용상태 대출정지기간의 정보를 정리
	private ArrayList<BookUseStatusVO> getNotReturnedBooks(ArrayList<BookUseStatusVO> bookList, Calendar criteriaDate){
		ArrayList<BookUseStatusVO> result = null;
		result = new ArrayList<BookUseStatusVO>();
		Timestamp stopServiceDate = null;
		//미반납도서 - 반납기간이 지나고 반납날짜가 비어있는 도서
		String userId = null;
		for(BookUseStatusVO book : bookList) {
			if( book.getBorrowEnd().getTime() <  criteriaDate.getTimeInMillis()&&book.getReturnDate()==null) {
				userId = book.getUserId();
				result.add(book);
				//System.out.println(book);
				Timestamp temp = DateCalculation.calcuStopDate(new Timestamp(criteriaDate.getTimeInMillis()), book.getBorrowEnd());
				if(stopServiceDate!=null) {
					stopServiceDate = temp.getTime()-stopServiceDate.getTime()>0?temp:stopServiceDate;
				} else {
					stopServiceDate = temp;
				}
			}
		}
		
		if(stopServiceDate!=null) {
			//DB user 정보 update
			try {
				dao.updateUserStopStatus(userId, stopServiceDate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return result;
	}
	
	private ArrayList<BookUseStatusVO> getReturnedBooks(ArrayList<BookUseStatusVO> bookList){
		ArrayList<BookUseStatusVO> result = null;
		//기반납도서
		result = new ArrayList<BookUseStatusVO>();
		for(BookUseStatusVO book : bookList) {
			if(book.getReturnDate()!=null) {
				//&&book.getBorrowEnd().getTime() >= book.getReturnDate().getTime() 조건에서 지움
				result.add(book);
			}
		}
		return result;
	}
	
	private ArrayList<BookUseStatusVO> getExpectingReturnBooks(ArrayList<BookUseStatusVO> bookList, Calendar criteriaDate){
		ArrayList<BookUseStatusVO> result = null;
		//반납예정도서
		result = new ArrayList<BookUseStatusVO>();
		for(BookUseStatusVO book : bookList) {
			if(book.getReturnDate()==null&&book.getBorrowEnd().getTime() >= criteriaDate.getTimeInMillis()) {
				result.add(book);
				//System.out.println(book);
			}
		}
		return result;
	}
	
	
	public HashMap<String, Object> getUserStatus(String userId,String startMonth) throws RuntimeException{
		HashMap<String, Object> result = null;
		result = new HashMap<String, Object>();
		
		UserVO user = null;
		ArrayList<BookUseStatusVO> list = null;
	
		try {
			
			list = dao.selectBookInfoByUser(userId, startMonth);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar criteriaDate = Calendar.getInstance();
		criteriaDate.set(2023, 5, 22);
		ArrayList<BookUseStatusVO> allReturned = this.getReturnedBooks(list);
		ArrayList<BookUseStatusVO> notReturned = this.getNotReturnedBooks(list, criteriaDate);
		ArrayList<BookUseStatusVO> expectingReturn = this.getExpectingReturnBooks(list,criteriaDate);
		
		try {
			user = dao.selectUser(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, ArrayList<BookUseStatusVO>> bookInfo = new HashMap<>();
		bookInfo.put("total", list);
		bookInfo.put("allReturned", allReturned);
		bookInfo.put("notReturned", notReturned);
		bookInfo.put("expectingReturn", expectingReturn);
		
		UserBookStatusVO userState = new UserBookStatusVO();
		userState.setUserId(userId);
		userState.setAvailableBook(user.getAvailableBook());
		userState.setExpectingReturnBook(expectingReturn.size());
		userState.setTotalUsingBook(list.size());
		userState.setReturnedBook(allReturned.size());
		userState.setNotReturnedBook(notReturned.size());
		

		if(notReturned.size()>0) {
			userState.setUserState("대출정지");
			userState.setStopDate(DateCalculation.getDate(user.getServiceStop()));
		} else {
			userState.setUserState("정상");
			userState.setStopDate("-");
		}
		HashMap<String, UserBookStatusVO> userInfo = new HashMap<>();
		
		userInfo.put("user", userState);
		result.put("userInfo", userInfo);
		result.put("bookInfo", bookInfo);
		
		return result;
	}
	
	
	//반납하기
	public void returnBook(String userId, int bookSeq) {
		Connection con = manager.getConnection();
		try {
			con.setAutoCommit(false);
			 dao.updateUserStatus(userId, bookSeq, con);
			 dao.updateUser(userId, con);
			 dao.updateBookCopy(userId, bookSeq, con);
			
			con.commit();
		} catch (SQLException e) {
			
		}finally {
			manager.closeConnection(null, null, con);
		}
		
	}
	
	
	//대출유효성 검사하기
	public boolean rentValidation(String userId, int bookSeq,Connection con) {
		//System.out.println(dao.selectUserBook(userId, con));
		//System.out.println(dao.selectBorrowEnd(userId, con));
		//System.out.println(dao.selectBookStatus(bookSeq, con));
		
			if(!dao.selectUserBook(userId, con)) {
				throw new RentFailedException();
			}else if(!dao.selectUserServiceStop(userId, con)) {
				throw new ServiceStopException();
			}
			else if(!dao.selectBorrowEnd(userId, con)) {
				throw new NotRentedBookException();
			} else if(!dao.selectBookStatus(bookSeq, con)) {
				throw new RentedBookException();
			}else {
				return true;
			}
		
		
		
	}
	//대출하기
	public boolean rentBook(String userId, int bookSeq) {
		boolean isRented = false;
		Connection con = manager.getConnection();
		try {
			boolean flag = this.rentValidation(userId, bookSeq,con);
			if(!flag) {
				return isRented;
			}else {
				con.setAutoCommit(false);
				dao.insertUserStatus(userId, bookSeq,con);
				dao.updateUserMaxBookDown(userId, con);
				dao.updateBookCopyUp(userId, bookSeq, con);
				
				
				con.commit();
				isRented = true;
			}
			
		} catch (SQLException e) {
			
		}
			manager.closeConnection(null, null, con);
			return isRented;
	}
	
}
