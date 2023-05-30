package bitedu.bipa.gisadb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class ConnectionManager {

	public static Connection getConnection() {
		Connection conn = null;
		
		String jdbcURL="";
		String driver = "";
		String id = "";
		String password = "";
		
		File file = new File("./data/db/db.properties");
		//파일스트림 사용
		
		try {
			FileReader rf = new FileReader(file);
			BufferedReader br = new BufferedReader(rf);
			
			jdbcURL = br.readLine().split("=")[1].trim();
			driver = br.readLine().split("=")[1].trim();
			id = br.readLine().split("=")[1].trim();
			password = br.readLine().split("=")[1].trim();
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		//properties class 사용
		/*Properties properties = new Properties();
		try {
			properties.load(new FileReader(file));
			jdbcURL = (String) properties.get("jdbcURL");
			driver = (String) properties.get("driver");
			id = (String) properties.get("id");
			password = (String) properties.get("password");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		try {
			conn = DriverManager.getConnection(
					jdbcURL,
					id,
					password
					);
			
			
				Class.forName(driver);
			 
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("연결 성공");
		return conn;
	}
	
	

	
	
	public static void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		
		if(stmt !=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
