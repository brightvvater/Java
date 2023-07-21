package bitedu.bipa.tiles.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.tiles.vo.User;

@Repository
public class MemberDao {
	
	private final SqlSession session;
	
	@Autowired
	public MemberDao(SqlSession session) {
		this.session = session;
	}
	
	public int insertMember(User user) {
		int affectedCount = session.insert("mapper.member.insertMember",user);
		return affectedCount;
	}
	
	public User selectById(String userId) {
		User user = session.selectOne("mapper.member.selectById",userId);
		return user;
	}
	
	public List<User> selectList() {
		List<User> list = session.selectList("mapper.member.selectList");
		return list;
	}

	public int deleteUser(String userId) {
		int affectedCount = session.delete("mapper.member.deleteUser", userId);
		
		return affectedCount;
	}
}
