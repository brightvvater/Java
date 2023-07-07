package bitedu.bipa.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import bitedu.bipa.book.utils.ConnectionManager;
import bitedu.bipa.book.vo.BookCopy;

@Component
public class BlmDAO {
	
	private static BlmDAO dao;
	private ConnectionManager manager = ConnectionManager.getInstance();
	
	private BlmDAO() {
	}
	
	public static BlmDAO getInstance() {
		if(dao ==null ) {
			dao  = new BlmDAO();
		}
		
		return dao;
	}
	public boolean insertBook(BookCopy copy){
		boolean flag = false;
		String sql1 = "insert into book_info values (?,?,?,?)";
		String sql2 = "insert into book_copy(book_isbn) values (?)";
		Connection con = manager.getConnectionOld();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, copy.getIsbn());
			pstmt.setString(2, copy.getTitle());
			pstmt.setString(3, copy.getAuthor());
			pstmt.setDate(4, copy.getPublishDate());
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, copy.getIsbn());
				affectedCount = pstmt.executeUpdate();
				if(affectedCount>0) {
					flag = true;
					con.commit();
					System.out.println("commit");
				}
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
				System.out.println("rollback");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(true);
				manager.closeConnection(null, pstmt, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return flag;
	}
	
	public ArrayList<BookCopy> selectBookAll(){
		
		//System.out.println("dao.....................");
		ArrayList<BookCopy> list = null;
		list = new ArrayList<BookCopy>();
		BookCopy copy = null;
		StringBuilder sb = new StringBuilder("select a.*, b.* from book_info a ");
		sb.append("inner join book_copy b on a.book_isbn=b.book_isbn");
		String sql = sb.toString();
		Connection con = manager.getConnectionOld();
		
		//System.out.println("con: "+con);
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				copy = new BookCopy();
				copy.setIsbn(rs.getString(1));
				copy.setTitle(rs.getString(2));
				copy.setAuthor(rs.getString(3));
				copy.setPublishDate(rs.getDate(4));
				copy.setBookSeq(rs.getInt(5));
				copy.setBookPosition(rs.getString(6));
				copy.setBookStaus(rs.getString(7));
				list.add(copy);
			}
			manager.closeConnection(rs, pstmt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public int selectTotalCount(){
		
		//System.out.println("dao.....................");
		int count = 0;
		StringBuilder sb = new StringBuilder("select count(*) as c from book_copy");
		String sql = sb.toString();
		Connection con = manager.getConnectionOld();
		
		//System.out.println("con: "+con);
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("c");
			}
			manager.closeConnection(rs, pstmt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	public boolean deleteBook(int parseInt) {
		boolean flag = false;
		Connection con = manager.getConnectionOld();
		PreparedStatement pstmt = null;
		String sql = "delete from book_copy where book_seq=?;";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, parseInt);
			int affected = pstmt.executeUpdate();
			if(affected > 0) {
				flag = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection(null, pstmt, con);
		}
		
		return flag;
	}
	
	public BookCopy bookDetail(int bookSeq) {
		
		Connection con = manager.getConnectionOld();
		BookCopy bc = new BookCopy();
		StringBuilder sb = new StringBuilder("select a.*, b.* from book_info a ");
		sb.append("inner join book_copy b on a.book_isbn=b.book_isbn  where book_seq=?");
		String sql = sb.toString();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookSeq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				bc.setIsbn(rs.getString(1));
				bc.setTitle(rs.getString(2));
				bc.setAuthor(rs.getString(3));
				bc.setPublishDate(rs.getDate(4));
				bc.setBookSeq(rs.getInt(5));
				bc.setBookPosition(rs.getString(6));
				bc.setBookStaus(rs.getString(7));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection(rs, pstmt, con);
		}
		return bc;
	}
	
	public boolean bookUpdate(BookCopy bookCopy) {
		boolean flag = false;
		Connection con = manager.getConnectionOld();
		PreparedStatement pstmt = null;
		String sql = "update book_info set book_isbn = ?, book_title =?, book_author=?, book_published_date=? where book_isbn = ?;";
		
		String sql2 = "update book_copy set book_position = ?, book_status = ?, book_isbn = ? where book_seq = ?;";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookCopy.getIsbn());
			pstmt.setString(2, bookCopy.getTitle());
			pstmt.setString(3, bookCopy.getAuthor());
			pstmt.setDate(4, bookCopy.getPublishDate());
			pstmt.setString(5, bookCopy.getIsbn());
			int affected = pstmt.executeUpdate();
			System.out.println(affected);
			if (affected > 0) {
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, bookCopy.getBookPosition());
				pstmt.setString(2, bookCopy.getBookStaus());
				pstmt.setString(3, bookCopy.getIsbn());
				pstmt.setInt(4, bookCopy.getBookSeq());
				pstmt.executeUpdate();
				flag = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection(null, pstmt, con);
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
				Connection con = manager.getConnectionOld();
				
				//System.out.println("con: "+con);
				PreparedStatement pstmt;
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, startCount);
					pstmt.setInt(2, totalPageCount);
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next()) {
						copy = new BookCopy();
						copy.setIsbn(rs.getString(1));
						copy.setTitle(rs.getString(2));
						copy.setAuthor(rs.getString(3));
						copy.setPublishDate(rs.getDate(4));
						copy.setBookSeq(rs.getInt(5));
						copy.setBookPosition(rs.getString(6));
						copy.setBookStaus(rs.getString(7));
						list.add(copy);
					}
					manager.closeConnection(rs, pstmt, con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return list;
	}
	
}
