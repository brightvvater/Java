package bitedu.bipa.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.board.dao.BoardLayerDao;
import bitedu.bipa.board.dao.BoardLayerDao2;
import bitedu.bipa.board.vo.BoardVO;
import bitedu.bipa.board.vo.ReplyVO;

@Service
public class BoardLayerService2 {
	
	@Autowired
	private BoardLayerDao2 dao;
	
	public List<BoardVO> showList() {
		List<BoardVO> list =  dao.selectAllList();
		
		return list;
	}
	
	public boolean registBoard(BoardVO vo) {
		boolean flag = false;
		
		flag = dao.insertBoard(vo);
		return flag;
	}
	
	public BoardVO showDetail(int boardSeq) {
		BoardVO vo = dao.selectBoard(boardSeq);
		return vo;
	}
	
	//댓글달기
	public boolean registReply(ReplyVO vo) {
		//만약 groupId가 0이라면 depth는 0 groupId는 본인의 boardSeq임
		
		int groupId = vo.getGroupId();
		if(groupId !=0) {//상위 댓글의 대댓글이라는 뜻 
			//groupId 가 있다면 depth는 1
			int depth = 1;
			vo.setDepth(depth);
			// groupOrd는 해당 depth의 max Ord를 가져와서 +1
			int groupOrd = dao.selectOrd(depth, vo.getGroupId());
			groupOrd = groupOrd == 0? 1: groupOrd;
			vo.setGroupOrd(groupOrd);
		
		} 

		boolean flag1 = dao.insertReply(vo);
		
		boolean flag2 = false;
		if(groupId ==0) { //원댓글
			//댓글 replyId 받아와서 groupId로 세팅해주기
			int replyId = dao.selectReplyId();
			flag2 = dao.updateGroupId(replyId);
			
		}
		return flag1 && flag2;
		
	}
	
	//댓글리스트 가져오기
	public List<ReplyVO> readReply(int boardSeq) {
		List<ReplyVO> list = dao.selectReply(boardSeq);
		
		return list;
	}
	
	//마지막 댓글 받아오기
	public ReplyVO readRepl() {
		int replyId = dao.selectReplyId();
		ReplyVO vo = dao.selectReplyByReplyId(replyId);
		
		return vo;
	}

	
}
