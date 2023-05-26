package bitedu.bipa.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDao {
	

	
	public void list() {
		Connection conn = null;
		 try { 
			 
			 String sql = "" +
				 "SELECT bno, btitle, bcontent, bwriter, bdate " 
				 + "FROM boards " 
				 +"ORDER BY bno DESC"; 
			 
		  conn = ConnectionManager.getConnection();	 
		  PreparedStatement pstmt = conn.prepareStatement(sql);
		  ResultSet rs = pstmt.executeQuery(); 
		  
		  while(rs.next()) { 
			  BoardDTO board = new BoardDTO(); 
			  board.setBno(rs.getInt("bno"));
			  board.setBtitle(rs.getString("btitle"));
			  board.setBcontent(rs.getString("bcontent"));
			  board.setBwriter(rs.getString("bwriter"));
			  board.setBdate(rs.getDate("bdate"));
		  System.out.printf("%-6s%-12s%-16s%-40s \n", board.getBno(), board.getBwriter(), board.getBdate(), board.getBtitle()); 
		  } 
		  rs.close();
		  pstmt.close(); 
		  } catch(SQLException e) {
			  e.printStackTrace(); 
			  ConnectionManager.exit(conn); 
		  }
	}
	
	public void create(BoardDTO board) {
		Connection conn = null;
		try { 
			String sql = "" +
		  "INSERT INTO boards (btitle, bcontent, bwriter, bdate) " +
		  "VALUES (?, ?, ?, now())"; 
		
		conn = ConnectionManager.getConnection();
		PreparedStatement pstmt =conn.prepareStatement(sql);
		
		pstmt.setString(1, board.getBtitle());
		pstmt.setString(2, board.getBcontent()); 
		pstmt.setString(3,board.getBwriter()); 
		pstmt.executeUpdate();
		pstmt.close(); 
		} catch (Exception e) {
			e.printStackTrace(); 
			
		} 
		ConnectionManager.exit(conn); 
	}
	
	public BoardDTO read(int bno) {
		
		BoardDTO board = new BoardDTO(); 
		Connection conn = null;
		try {
			String sql = "" +
				  "SELECT bno, btitle, bcontent, bwriter, bdate " + "FROM boards " +
				  "WHERE bno=?"; 
		
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno); 
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate")); 		  
			}
			pstmt.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		ConnectionManager.exit(conn); 
		return board;
		
	}
				  
				  
	public void update(BoardDTO board) {
		
		Connection conn = null;
		try { 
			String sql = "" +
					"UPDATE boards SET btitle=?, bcontent=?, bwriter=? " + "WHERE bno=?";
			
		
			 conn = ConnectionManager.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1,board.getBtitle()); 
			 pstmt.setString(2, board.getBcontent());
			 pstmt.setString(3, board.getBwriter()); 
			 pstmt.setInt(4, board.getBno());
			 pstmt.executeUpdate(); 
			 pstmt.close(); 
			 
		} catch (Exception e) {
			   	 e.printStackTrace();
			   	 
		}
		ConnectionManager.exit(conn); 
	}
	
	public void delete(BoardDTO board) {
		Connection conn = null;
		try { 
			String sql ="DELETE FROM boards WHERE bno=?"; 
			
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, board.getBno());
			pstmt.executeUpdate(); 
			pstmt.close(); 
		} catch (Exception e) {
			 e.printStackTrace(); 
			 
		}	
		ConnectionManager.exit(conn); 
	}
	
	public void clear() {
		Connection conn = null;
		try { 
			String sql = "TRUNCATE TABLE boards"; 
			
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.executeUpdate(); 
			pstmt.close(); 
			
			}
		catch (Exception e) {
			e.printStackTrace(); 
			
		}
		ConnectionManager.exit(conn); 
	}
	
}
	
