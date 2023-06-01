package bitedu.bipa.librarysystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalHistoryDAO {
	
	//대출내역 조회하기
	public List<ReturnDTO> selectRentalHistories(int userNo) throws SQLException {
		List<ReturnDTO> list = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("select b.book_no, author, rental_date, expect_return_date, history_no, return_date ");
		sb.append("from book as b join rentalhistory as r ");
		sb.append("where b.book_no = r.book_no ");
		sb.append("and user_no = ? and return_date is null");
		
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setInt(1, userNo);
		ResultSet rs =  pstmt.executeQuery();
		
		while(rs.next()) {
			ReturnDTO dto = new ReturnDTO();
			dto.setBookNo(rs.getInt(1));
			dto.setAuthor(rs.getString(2));
			dto.setRentalDate(rs.getDate(3).toLocalDate());
			dto.setExpectedReturnDate(rs.getDate(4).toLocalDate());
			dto.setHistoryNo(rs.getInt(5));
			
			list.add(dto);
		}
		ConnectionManager.closeConnection(rs, pstmt, conn);
		
		return list;
	}
	
	
	
	//반납하기
	public boolean updateRentalHistories(int historyNo, int bookNo) throws SQLException {
		boolean flag = true;
		Connection conn = ConnectionManager.getConnection();
		conn.setAutoCommit(false);
		
		
		//반납일 추가하기
		String sql = "update rentalhistory set return_date = sysdate() where history_no=?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, historyNo);
		int updateHistory = pstmt.executeUpdate();
		
		//도서에 대출여부 update 하기
		sql = "update book set rental_check ='F' where book_no = ?;";
		pstmt.setInt(1, bookNo);
		int updateBook = pstmt.executeUpdate();
		
		if(updateHistory ==0 || updateBook ==0) {
			return false;
		}
		conn.commit();
		ConnectionManager.closeConnection(null, pstmt, conn);
		
		
		return flag;
	}
	

	
	
	//연체일자 확인하기
	public int checkOverDue(int historyNo) {
		int count = 0;
		Connection conn = ConnectionManager.getConnection();
		String sql = "select expect_return_date, return_date from rentalhistory where history_no = ?;";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, historyNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				LocalDate expectDate = rs.getDate(1).toLocalDate();
				LocalDate returnDate = rs.getDate(2).toLocalDate();
				
				count = returnDate.getDayOfYear()-expectDate.getDayOfYear(); //연체일자 : 반납일자 - 반납예정일자	
			}
			
			ConnectionManager.closeConnection(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	//연체일자만큼 user테이블에 넣어주기
	public void insertOverDue(int count, int userNo) {
		Connection conn = ConnectionManager.getConnection();
		String sql = "update user set overdue_date =? where user_no = ?;";
		
		int year = LocalDate.now().getYear();
		int overDueDate = (LocalDate.now().getDayOfYear() + count);
		
		if(overDueDate>365) {
			year += overDueDate/365;
			overDueDate%=365;
		}
		//System.out.println(LocalDate.ofYearDay(year, overDueDate));
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(LocalDate.ofYearDay(year, overDueDate)));
			pstmt.setInt(2, userNo);
			
			int updateCount = pstmt.executeUpdate();
			if(updateCount ==0) {
				System.out.println("연체일자 입력 실패");
			}
			
			ConnectionManager.closeConnection(null, pstmt, conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
