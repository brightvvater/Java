package bitedu.bipa.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.board.utils.ConnectionManager;
import bitedu.bipa.board.vo.BoardVO;

@Repository
public class BoardLayerDao {
	
	@Autowired
	private ConnectionManager manager;
	
	public List<BoardVO> selectAllList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection con = manager.getConnection();
		String sql = "select * from board";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()) {
				int boardSeq = rs.getInt("board_seq");
				String boardTitle = rs.getString("b_title");
				String userId = rs.getString("user_id");
				int view = rs.getInt("view");
				Timestamp regDate = rs.getTimestamp("b_reg_date");
				
				BoardVO vo = new BoardVO(boardSeq, boardTitle, userId, userId, view, regDate);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public  boolean insertBoard(BoardVO vo) {
		boolean flag = false;
		Connection con = manager.getConnection();
		String sql = "insert into board (b_title, user_id, b_content, view, b_reg_date) values (?,?,?,?,?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getBoardTitle());
			pstmt.setString(2, vo.getUserId());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getView());
			pstmt.setTimestamp(5, vo.getRegDate());
			
			int affectedCount = pstmt.executeUpdate();
			
			if(affectedCount>0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return flag;
	}
	
	
	public BoardVO selectBoard(int boardSeq) {
		BoardVO vo = null;
		Connection con = manager.getConnection();
		String sql = "select * from board where board_seq= ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardSeq);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()) {
				String boardTitle = rs.getString("b_title");
				String userId = rs.getString("user_id");
				int view = rs.getInt("view");
				String content = rs.getString("b_content");
				Timestamp regDate = rs.getTimestamp("b_reg_date");
				
				
				vo = new BoardVO(boardSeq,boardTitle, userId, content, view, regDate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	

}
