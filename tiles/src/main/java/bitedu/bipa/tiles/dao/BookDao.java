package bitedu.bipa.tiles.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.tiles.vo.BookCopy;

@Repository
public class BookDao {
	
	private final SqlSession session;
	
	@Autowired
	public BookDao (SqlSession session) {
		this.session = session;
	}
	
	public List<BookCopy> selectList() {
		List<BookCopy> list = (List)session.selectList("mapper.book.selectList");
		
		return list;
	}
	
}
