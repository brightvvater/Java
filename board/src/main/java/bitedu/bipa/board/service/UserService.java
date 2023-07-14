package bitedu.bipa.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.board.dao.UserDao;
import bitedu.bipa.board.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;

	public boolean login(UserVO vo) {
		boolean  isExist = dao.selectUser(vo.getUserId());
		
		return isExist;
	}

}
