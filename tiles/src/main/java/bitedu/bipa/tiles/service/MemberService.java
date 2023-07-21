package bitedu.bipa.tiles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.tiles.dao.MemberDao;
import bitedu.bipa.tiles.vo.User;

@Service
public class MemberService {
	
	private final MemberDao dao;
	
	@Autowired
	public MemberService(MemberDao dao) {
		this.dao = dao;
	}
	
	public boolean registUser(User user) {
		boolean flag = false;
		int affectedCount = dao.insertMember(user);
		if(affectedCount>0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean validationUserId(String userId) {
		boolean flag = true;
		User dbUser = dao.selectById(userId);
		if(dbUser !=null) {
			flag = false;
		}
		
		return flag;
	}
	
	public boolean confirmUser(User user) {
		boolean flag = false;
		User dbUser = dao.selectById(user.getUserId());
		if(dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
			flag = true;
		}
		
		return flag;
	}
	
	public List<User> showList() {
		List<User> list = dao.selectList();
		
		return list;
	}
	
	public User showDetail(String userId) {
		User user =  dao.selectById(userId);
		return user;
	}
	
	public boolean removeUser(String userId) {
		int affectedCount = dao.deleteUser(userId);
		if( affectedCount>0) {
			return true;
		}
		return false;
	}

}
