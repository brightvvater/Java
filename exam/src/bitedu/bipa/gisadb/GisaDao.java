package bitedu.bipa.gisadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GisaDao {
	
	
	//DB관 작업 전용 클래스 
	public int selectQuiz1(String sql) {
		int stdNo = 0;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				stdNo = rs.getInt(1);
			}
			ConnectionManager.closeConnection(rs, pstmt, conn);
			System.out.println("연결 종료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stdNo;
	}
	
		
		
	

	
}
