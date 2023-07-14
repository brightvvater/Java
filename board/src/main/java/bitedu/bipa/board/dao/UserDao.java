package bitedu.bipa.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.board.utils.ConnectionManager;

@Repository
public class UserDao {
	
	@Autowired
	private ConnectionManager manager;

	public boolean selectUser(String userId) {
		boolean flag = false;
		Connection con = manager.getConnection();
		String sql = "select * from book_user where user_id=?";
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return flag;
	}

}
