package bitedu.bipa.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.board.dao.BoardLayerDao;
import bitedu.bipa.board.vo.BoardVO;

@Service
public class BoardLayerService {
	
	@Autowired
	private BoardLayerDao dao;
	
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

}
