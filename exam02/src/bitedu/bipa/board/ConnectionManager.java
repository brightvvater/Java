package bitedu.bipa.board;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	
	//Constructor
	public static Connection getConnection() {
		
		Connection conn = null;
		String jdbcURL = "";
		String driver = "";
		String id = "";
		String password = "";
		
		
		
		try {
			Properties properties = new Properties();
			properties.load(new FileReader("./data/db.properties"));
			
			jdbcURL = (String) properties.get("jdbcURL");
			driver = (String) properties.get("driver");
			id = (String) properties.get("id");
			password = (String) properties.get("password");
			//JDBC Driver 등록
			Class.forName(driver);
			
			//연결하기
			conn = DriverManager.getConnection(
				jdbcURL, 
				id, 
				password
				
			);
			
			
		} catch(Exception e) {
			e.printStackTrace();

		}
		
		return conn;
	}
	
	public static void exit(Connection conn) {
		if(conn != null) {
			try { 
				conn.close(); 
			} catch(SQLException e) {} 
		} 
	}
	

	
	
}
