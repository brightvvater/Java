package bitedu.bipa.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.board.utils.ConnectionManager;
import bitedu.bipa.board.vo.BoardVO;
import bitedu.bipa.board.vo.ReplyVO;

@Repository
//dataSource 사용
public class BoardLayerDao2 {
	
	@Autowired
	private DataSource dataSource;
	
	public List<BoardVO> selectAllList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		System.out.println(dataSource);
		try {
			Connection con = dataSource.getConnection();
			String sql = "select * from board";
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
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public  boolean insertBoard(BoardVO vo) {
		boolean flag = false;
		
		String sql = "insert into board (b_title, user_id, b_content, view, b_reg_date) values (?,?,?,?,?);";
		try {
			Connection con = dataSource.getConnection();
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
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return flag;
	}
	
	
	public BoardVO selectBoard(int boardSeq) {
		BoardVO vo = null;
		
		String sql = "select * from board where board_seq= ?";
		try {
			Connection con = dataSource.getConnection();
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
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	//댓글 마지막 번호 받아오기
	public int selectReplyId() {
		String sql = "select max(reply_id) as reply_id from reply";
		int replyId = 0;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				replyId = rs.getInt("reply_id");
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return replyId;
	}
	
	//마지막 댓글 불러오기
	public ReplyVO selectReplyByReplyId(int replyId) {
		String sql = "select *  from reply where reply_id = ?";
		ReplyVO vo = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, replyId);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				int boardSeq = rs.getInt("board_seq");
				String userId = rs.getString("user_id");
				String content = rs.getString("r_content");
				Timestamp regDate = rs.getTimestamp("r_reg_date");
				
				vo = new ReplyVO(replyId, boardSeq, userId, content, regDate, 0, 0, 0);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
		
	
	
		//해당 depth의 order가져오기
		public int selectOrd(int depth, int groupId) {
			String sql = "select max(group_ord)+1 as group_ord from reply where depth=? and group_id=?";
			int groupOrd = 0;
			try {
				Connection con = dataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, depth);
				pstmt.setInt(2,groupId);
				ResultSet rs =  pstmt.executeQuery();
				if(rs.next()) {
					groupOrd = rs.getInt("group_ord");
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return groupOrd;
		}
	
	//댓글 넣기
	public boolean insertReply(ReplyVO vo) {
		boolean flag = false;
		String sql = "insert into reply (board_seq, user_id, r_content, r_reg_date, depth, group_id,group_ord) values (?,?,?,?,?,?,?)";
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getBoardSeq());
			pstmt.setString(2, vo.getUserId());
			pstmt.setString(3, vo.getrContent());
			pstmt.setTimestamp(4, vo.getRegDate());
			pstmt.setInt(5, vo.getDepth());
			pstmt.setInt(6, vo.getGroupId());
			pstmt.setInt(7, vo.getGroupOrd());
			
			int affectedCount =  pstmt.executeUpdate();
			if(affectedCount>0) {
				flag= true;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}


	//groupId 올리기
	public boolean updateGroupId(int replyId) {
		boolean flag = false;
		String sql = "update reply set group_id = ? where reply_id = ?";
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, replyId);
			pstmt.setInt(2, replyId);
			
			int affectedCount =  pstmt.executeUpdate();
			if(affectedCount>0) {
				flag= true;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	
	//해당 게시물의 댓글 가져오기
	public List<ReplyVO> selectReply(int boardSeq) {
		String sql = "select * from reply where board_seq= ?";
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		ReplyVO vo = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardSeq);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()) {
				int replyId = rs.getInt("reply_id");
				String userId = rs.getString("user_id");
				String rContent = rs.getString("r_content");
				Timestamp regDate = rs.getTimestamp("r_reg_date");
				int depth = rs.getInt("depth");
				int groupId = rs.getInt("group_id");
				int groupOrd = rs.getInt("group_ord");
			    vo = new ReplyVO(replyId, boardSeq, userId, rContent, regDate, depth, groupId, groupOrd);
			    list.add(vo);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
