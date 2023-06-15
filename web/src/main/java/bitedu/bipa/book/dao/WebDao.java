package bitedu.bipa.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bitedu.bipa.book.dto.BookDTO;
import bitedu.bipa.book.utils.ConnectionManager;

public class WebDao {
	
	public List<BookDTO> selectBookList() throws SQLException {
		List<BookDTO> list = new ArrayList();
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from book";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int bookno = rs.getInt("book_no");
			String title = rs.getString("title");
			String content = rs.getString("content");
	        String author = rs.getString("author");
	        String category = rs.getString("category_name");
	        list.add(new BookDTO(bookno, title,content, author,category));
		}
		return list;
		
	}

}
