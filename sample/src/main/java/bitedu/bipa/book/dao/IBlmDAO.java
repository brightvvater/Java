package bitedu.bipa.book.dao;

import java.util.ArrayList;

import bitedu.bipa.book.vo.BookCopy;

public interface IBlmDAO {
	
	public boolean insertBook(BookCopy vo);
	public ArrayList<BookCopy> selectBookAll();
	public boolean deleteBook(int parseInt);
	public BookCopy bookDetail(int bookSeq);
	public boolean bookUpdate(BookCopy bookCopy) ;
	public ArrayList<BookCopy> selectBookWithPaging(int startCount, int totalPageCount);
}
