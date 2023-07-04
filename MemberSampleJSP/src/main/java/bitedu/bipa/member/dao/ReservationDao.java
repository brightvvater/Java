package bitedu.bipa.member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import bitedu.bipa.member.utils.ConnectionManager;
import bitedu.bipa.member.vo.BookReserveStatusVO;
import bitedu.bipa.member.vo.BookUser;

public class ReservationDao {
	
	ConnectionManager manager;
	
	public ReservationDao() {
		this.manager = ConnectionManager.getInstance();
	}
	

	public BookUser selectUserServiceStopAndReserveCount(String userId) {
		BookUser user = new BookUser();
		Connection conn  = manager.getConnectionOld();
		String sql = "select service_stop, reserve_book from book_user where user_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				Date serviceStop = rs.getDate("service_stop");
				int reserve_book = rs.getInt("reserve_book");
				
				user.setReserveBook(reserve_book);
				if(serviceStop !=null) {
					user.setServiceStop(new Timestamp(serviceStop.getTime()));
				}
				
			}
			ConnectionManager.closeConnection(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	public String selectBookStatus(int bookSeq) {
		String bookStatus = "";
		Connection conn = manager.getConnectionOld();
		String sql = "select book_status from book_copy where book_seq = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookSeq);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				bookStatus = rs.getString("book_status");
			}
			ConnectionManager.closeConnection(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return bookStatus;
	}

	public int selectReserveCount(int bookSeq) {
		int reserveCount = 0;
		Connection conn = manager.getConnectionOld();
		String sql = "select count(*) as count from book_reserve_status where book_seq = ?";
	
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookSeq);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				reserveCount = rs.getInt("count");
			}
			ConnectionManager.closeConnection(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reserveCount;
	}

	public int insertReserveStatus(BookReserveStatusVO vo) {
		int affectedCount = 0;
		Connection conn = manager.getConnectionOld();
		String sql = "insert into book_reserve_status (book_seq, user_id, reserve_date) values(?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBookSeq());
			pstmt.setString(2, vo.getUserId());
			pstmt.setTimestamp(3, vo.getReserveDate());
			
			affectedCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return affectedCount;
	}


	public boolean reduceReserveBookCount(String userId) {
		boolean isReduced = true;
		Connection conn = manager.getConnectionOld();
		String sql = "update book_user set reserve_book= (reserve_book-1) where user_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userId);
			int affectedCount = pstmt.executeUpdate();
			
			if(affectedCount<=0) {
				isReduced = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isReduced;
	}

}
