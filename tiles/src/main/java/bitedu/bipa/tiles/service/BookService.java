package bitedu.bipa.tiles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.tiles.dao.BookDao;
import bitedu.bipa.tiles.vo.BookCopy;

@Service
public class BookService {
	
	private final BookDao dao;
	
	@Autowired
	public BookService(BookDao dao) {
		this.dao = dao;
	}
	
	public List<BookCopy> showList() {
		List<BookCopy> list =  dao.selectList();
		
		return list;
	}

}
