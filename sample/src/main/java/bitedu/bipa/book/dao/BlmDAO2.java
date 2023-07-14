package bitedu.bipa.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import bitedu.bipa.book.utils.ConnectionManager;
import bitedu.bipa.book.vo.BookCopy;

@Repository   
public class BlmDAO2 implements IBlmDAO{
	
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public boolean insertBook(BookCopy copy){
		boolean flag = false;
		int affectedCount1 = sqlSession.insert("mapper.book.insertBook",copy);
		int affectedCount2 = sqlSession.insert("mapper.book.insertCopy",copy);
		if(affectedCount1>0 && affectedCount2>0) {
			flag = true;
		}
	
		
		return flag;
	}
	
	public ArrayList<BookCopy> selectBookAll(){
		
		ArrayList<BookCopy> list = new ArrayList<BookCopy>();
		list = (ArrayList)sqlSession.selectList("mapper.book.selectAllBook");
		//System.out.println(list.size());
		
		
		return list;
	}
	
	
	public int selectTotalCount(){
		
		int count = 0;
		StringBuilder sb = new StringBuilder("select count(*) as c from book_copy");
		String sql = sb.toString();
	
		
		return count;
	}
	
	
	public boolean deleteBook(int parseInt) {
		boolean flag = false;
		int affectedCount = sqlSession.delete("mapper.book.deleteBook",parseInt);
		if(affectedCount>0) {
			flag = true;
		}
		
		return flag;
	}
	
	public BookCopy bookDetail(int bookSeq) {
		
		BookCopy bc = new BookCopy();
		bc = sqlSession.selectOne("mapper.book.selectBookBySeq", bookSeq);
		
		return bc;
	}
	
	public boolean bookUpdate(BookCopy bookCopy) {
		System.out.println("update................");
		boolean flag = false;
		int affectedCount = sqlSession.update("mapper.book.updateBook",bookCopy);
		if(affectedCount>0) {
			flag = true;
		}
		return flag;
	}
	
	//페이징 된 list 가져오기
	public ArrayList<BookCopy> selectBookWithPaging(int startCount, int totalPageCount) {
		
		ArrayList<BookCopy> list = null;
		list = new ArrayList<BookCopy>();
		BookCopy copy = null;
		StringBuilder sb = new StringBuilder("select a.*, b.* from book_info a ");
		sb.append("inner join book_copy b on a.book_isbn=b.book_isbn");
		sb.append(" limit ?,?");
		String sql = sb.toString();
				
				
		return list;
	}
	
}
