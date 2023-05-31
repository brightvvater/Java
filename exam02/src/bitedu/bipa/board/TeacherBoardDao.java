package bitedu.bipa.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherBoardDao {

	public boolean insert(BoardDTO item) throws SQLException {
		boolean flag = false;
		Connection con = ConnectionManager.getConnection();
		String sql = "insert into boards(btitle, bcontent, bwriter) values(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, item.getBtitle());
		pstmt.setString(2, item.getBcontent());
		pstmt.setString(3, item.getBwriter());
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount>0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, con);
		
		return flag;
	}
	
	public BoardDTO select(int bno) throws SQLException {
		BoardDTO item = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from boards where bno =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bno);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			//sysout과 같은 로직이 들어오면 안된다. DB -> List 로 바꿔 저장하는 것이 주목적
			item = new BoardDTO(bno, rs.getString(2), rs.getString(3),rs.getString(4),rs.getDate(5));
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return item;
	}
	
	//delete, deleteAll 모두 처리
	public boolean delete(String sql) throws SQLException {
		boolean flag = false;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount>0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, con);
		return flag;
	}
	
	public ArrayList<BoardDTO> selectAll() throws SQLException {
		ArrayList<BoardDTO> list = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from boards";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		list = new ArrayList<>();
		BoardDTO item = null;
		while(rs.next()) {
			//sysout과 같은 로직이 들어오면 안된다.connection을 계속 가지고 있을 수 있기 때문
			//DB -> List 로 바꿔 저장하는 것이 주목적
			item = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getDate(5));
			list.add(item);
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return list;
	}
	
	public boolean update(BoardDTO item) throws SQLException {
		boolean flag = false;
		Connection con = ConnectionManager.getConnection();
		String sql = "update boards set Btitle = ?, Bcontent = ?, Bwriter = ? where bno =? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, item.getBtitle());
		pstmt.setString(2, item.getBcontent());
		pstmt.setString(3, item.getBwriter());
		pstmt.setInt(4, item.getBno());

		int affectedCount = pstmt.executeUpdate();
		if(affectedCount>0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, con);
		return flag;
	}
	
} 
