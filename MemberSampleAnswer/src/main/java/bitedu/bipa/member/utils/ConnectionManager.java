package bitedu.bipa.member.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	
	private static ConnectionManager manager;
	
	private ConnectionManager() {
		
	}
	
	public static ConnectionManager getInstance() {
		if(manager == null) {
			manager = new ConnectionManager();
		}
		return manager;
	}
	
	public Connection getConnection() {
		Connection con = null;
		
		return con;
	}
	
	public void closeConnection(ResultSet rs, Statement stmt, Connection con) {
		if(rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt !=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con !=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs = null;
		stmt = null;
		con = null;
	}
}
