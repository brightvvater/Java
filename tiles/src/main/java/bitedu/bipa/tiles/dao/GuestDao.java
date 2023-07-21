package bitedu.bipa.tiles.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<Guest> selectListWithSearch(String selectBox, String searchBox) {
		//System.out.println(selectBox);
		//System.out.println(searchBox);
		Map<String, String> map = new HashMap<String, String>();
		map.put("selectBox", selectBox);
		map.put("searchBox", searchBox);
		List<Guest> list =  session.selectList("mapper.guest.selectList2",map);
		return list;
	}		

	public int insertGuest(Guest guest) {
		int affectedCount = session.insert("mapper.guest.insertGuest", guest);
		
		return affectedCount;
	}
}
