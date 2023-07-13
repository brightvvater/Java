package bitedu.bipa.book.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.book.dao.BlmDAO;
import bitedu.bipa.book.dao.BlmDAO2;
import bitedu.bipa.book.utils.PageDTO;
import bitedu.bipa.book.vo.BookCopy;

@Service
public class BlmService2 {
	
	@Autowired
	private BlmDAO2 dao;
	
	
	public boolean registBook(BookCopy copy) {
		boolean flag = false;
		flag = dao.insertBook(copy);
		return flag;
	}
	
	public ArrayList<BookCopy> searchBookAll(){
		
		ArrayList<BookCopy> list = null;
		list = dao.selectBookAll();
		
		return list;
	}
	
	
	
	public PageDTO<BookCopy> serchBookWithPaging(int currentPage) {
		ArrayList<BookCopy> list = null;
		int totalPageCount = 3;
		list = dao.selectBookWithPaging((currentPage-1)*totalPageCount, totalPageCount);
		
		int count = dao.selectTotalCount(); //전체 목록의 수 
		PageDTO<BookCopy> dto = new PageDTO<BookCopy>(currentPage, count, totalPageCount,list);
		
		return dto;
	}
	
	public boolean removeBook(String bookSeq) {
		
		boolean flag = false;
		flag = dao.deleteBook(Integer.parseInt(bookSeq));
		
		return flag;
	}
	
	public BookCopy bookDetail(String bookSeq) {
		BookCopy detail = dao.bookDetail(Integer.parseInt(bookSeq));
		
		return detail;
	}
	
	public boolean bookModify(BookCopy bookCopy) {
		boolean flag = false;
		flag = dao.bookUpdate(bookCopy);
		
		return flag;
	}
}
