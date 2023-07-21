package bitedu.bipa.tiles.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.tiles.vo.Guest;

@Repository
public class GuestDao {
	
	private SqlSession session;
	
	@Autowired
	public GuestDao(SqlSession session) {
		this.session = session;
	}
	
	public List<Guest> selectList() {
		List<Guest> list =  session.selectList("mapper.guest.selectList");
		
		return list;
	}

}
