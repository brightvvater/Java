package bitedu.bipa.member.service;

import java.util.ArrayList;

import bitedu.bipa.member.dao.BlmDAO;
import bitedu.bipa.member.vo.BookCopy;

public class BlmService {
	private BlmDAO dao;
	public BlmService() {
		dao = new BlmDAO();
	}
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
