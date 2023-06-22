package bitedu.bipa.quiz.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import bitedu.bipa.quiz.util.ConnectionManager;
import bitedu.bipa.quiz.vo.BookUseStatusVO;
import bitedu.bipa.quiz.vo.UserVO;

public class LibraryDAO {

	private ConnectionManager manager;
	
	// 비즈니스 판별은 로직에서
	public LibraryDAO() { 
		this.manager = ConnectionManager.getInstance();
	} 
	 
	
	public UserVO selectUser(String userId) throws SQLException {
		UserVO user = null;
		String sql = "select user_status,max_book,service_stop from book_user where user_id = ?";
		Connection con = manager.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			user = new UserVO();
			user.setUserId(userId);
			user.setUserState(rs.getString(1));
			user.setAvailableBook(rs.getInt(2));
			user.setServiceStop(rs.getTimestamp(3));
		}
		
		manager.closeConnection(rs, pstmt, con);
		return user;
	}
	

	public ArrayList<BookUseStatusVO> selectBookInfoByUser(String userId,String startMonth) throws SQLException, RuntimeException {
		ArrayList<BookUseStatusVO> list = null;
		list = new ArrayList<BookUseStatusVO>();
		StringBuilder sb = new StringBuilder("select i.book_isbn,i.book_title,i.book_author,s.* ");
		sb.append("from book_copy c  inner join (book_info i) on c.book_isbn = i.book_isbn ");
		sb.append("inner join book_use_status s on s.book_seq = c.book_seq ");
		sb.append("where s.user_id = ? and s.borrow_start between '2023-6-1' and '2023-6-30'");
		String sql = sb.toString();
		Connection con = manager.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		BookUseStatusVO vo = null;
		while(rs.next()) {
			vo = new BookUseStatusVO(rs.getInt(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getTimestamp(8));
			vo.setBookIsbn(rs.getString(1));
			vo.setBookTitle(rs.getString(2));
			vo.setBookAuthor(rs.getString(3));
			list.add(vo);
		}
		if(list.isEmpty()) {
			throw new RuntimeException();
		}
		manager.closeConnection(rs, pstmt, con);
		
		return list;
	}
	
	public boolean updateUserStopStatus(String userId, Timestamp stopDate) throws SQLException {
		boolean flag = false;
		String sql = "update book_user set user_status= ?, service_stop = ? where user_id = ?";
		try {
			Connection con = manager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "01");
			pstmt.setTimestamp(2, stopDate);
			pstmt.setString(3, userId);
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
				//System.out.println("success");
			}
			manager.closeConnection(null, pstmt, con);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return flag;
	}
	
	/*반납하기*/
	//반납 return_date 넣기
	public boolean updateUserStatus(String userId, int bookSeq, Connection con) {
		//System.out.println("userId:"+userId);
		//System.out.println("bookSeq:"+bookSeq);
		boolean flag = false;
		String sql = "update book_use_status set return_date=sysdate() where book_seq=? and user_id=?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookSeq);
			pstmt.setString(2, userId);
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}else if(affectedCount==0) {
				System.out.println("반납실패");
				con.rollback();
			}
			manager.closeConnection(null, pstmt, null);
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}
	
	//반납시 user 대출가능권수 올리기
	public boolean updateUser(String userId, Connection con) {
		boolean flag = false;
		String sql = "update book_user set max_book = max_book+1 where user_id=?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}else if(affectedCount==0) {
				System.out.println("반납실패");
				con.rollback();
			}
			manager.closeConnection(null, pstmt, null);
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	//반납시 책의 대출 상태 고치기
	public boolean updateBookCopy(String userId, int bookSeq, Connection con) {
		boolean flag = false;
		String sql = "update book_copy set book_status = 'BM-0001' where book_seq=?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookSeq);
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}else if(affectedCount==0) {
				System.out.println("반납실패");
				con.rollback();
			}
			manager.closeConnection(null, pstmt, null);
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	//반납시 연체여부 체크해서 연체일자 올리기
	//public boolean 
	
	
	/*대출하기*/
	//대출시 대출내역 만들기
	public boolean insertUserStatus(String userId, int bookSeq, Connection con) {
		boolean flag = false;
		String sql ="insert into book_use_status (book_seq, user_id, borrow_start, borrow_end) "+
				   "values(?,?,sysdate(),date_add(sysdate(),interval 14 day));";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookSeq);
			pstmt.setString(2, userId);
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}else if(affectedCount==0) {
				System.out.println("대출실패");
				con.rollback();
			}
			manager.closeConnection(null, pstmt, null);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return flag;
	}
	
	//대출시 대출 가능권수 줄이기
	public boolean updateUserMaxBookDown(String userId, Connection con) {
		boolean flag = false;
		String sql = "update book_user set max_book = max_book-1 where user_id=?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}else if(affectedCount==0) {
				System.out.println("대출실패");
				con.rollback();
			}
			manager.closeConnection(null, pstmt, null);
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	//대출시 책의 대출상태 고치기
	public boolean updateBookCopyUp(String userId, int bookSeq, Connection con) {
		boolean flag = false;
		String sql = "update book_copy set book_status = ? where book_seq=?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "BB-"+userId);
			pstmt.setInt(2, bookSeq);
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}else if(affectedCount==0) {
				System.out.println("대출실패");
				con.rollback();
			}
			manager.closeConnection(null, pstmt, null);
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	/*대출 유효성 검사하기*/
	//해당유저가 서비스 정지기간이 있는지, 해당 유저가 대출 권수를 초과하지 않았는지 확인
	public boolean selectUserBook(String userId, Connection con) {
		boolean flag =false;
		String sql ="select max_book, service_stop from book_user where user_id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int maxBook = rs.getInt("max_book");
				Date serviceStop = rs.getDate("service_stop");
				//System.out.println("maxBook: "+maxBook);
				//System.out.println("serviceStop: "+serviceStop);
				if(maxBook>0 && serviceStop == null) {
					flag = true; //대출가능
				}
			}
			
			manager.closeConnection(rs, pstmt, null);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return flag;
	}
	
	//해당유저가 미납중인 도서가 있는지 확인
	public boolean selectBorrowEnd(String userId, Connection con) {
		boolean flag = true;
		String sql ="select borrow_end from book_use_status where user_id = ? and return_date is null";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			Calendar today = Calendar.getInstance();
			today.set(2023, 5, 22);
			
			while(rs.next()) {
				Date borrowEnd = rs.getDate("borrow_end");
				//System.out.println(borrowEnd);
				
				//System.out.println(borrowEnd.after(today.getTime()));
				if(borrowEnd.before(today.getTime())) {
					return false;				
				}
					
			}
			manager.closeConnection(rs, pstmt, null);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return flag;
	}
	
	//해당 도서가 대출중인지 확인
	public boolean selectBookStatus(int bookSeq, Connection con) {
		boolean flag = false;
		String sql ="select book_status from book_copy where book_seq=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookSeq);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String bookStatus = rs.getString("book_status");
				if(bookStatus.equals("BM-0001")) {
					return true;
				}		
			}
			manager.closeConnection(rs, pstmt, null);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return flag;
	}
		
}
