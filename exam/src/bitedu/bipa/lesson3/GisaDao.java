package bitedu.bipa.lesson3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class GisaDao {
	
	//DB관 작업 전용 클래스 

	
	public int selectQuiz1(String sql) {
		int stdNo = 0;
		try {
			Connection conn = this.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				stdNo = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
			System.out.println("연결 종료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stdNo;
	}
	
		
		
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/new_schema",
				"root",
				"mysql");
		System.out.println("연결 성공");
		return conn;
	}
	
	
}
