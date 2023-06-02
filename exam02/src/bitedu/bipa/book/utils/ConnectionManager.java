package bitedu.bipa.book.utils;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            //TODO 3 : 상대경로 값으로 변경 (resources / properties)
            properties.load(new FileReader("./data/db.properties"));
            //properties.load(ClassLoader.getSystemResourceAsStream("DB"));

            jdbcURL = (String) properties.get("jdbcURL");
			driver = (String) properties.get("driver");
			id = (String) properties.get("id");
			password = (String) properties.get("password");
			//JDBC Driver 등록
			Class.forName(driver);
			/*
			 * jdbcURL = properties.getProperty("DB_URL"); id =
			 * properties.getProperty("DB_USER"); password =
			 * properties.getProperty("DB_PASSWORD");
			 */
            //JDBC Driver 등록
            //Class.forName(properties.getProperty("DRIVER"));

            //연결하기
            conn = DriverManager.getConnection(
                jdbcURL,
                id,
                password

            );


        } catch (Exception e) {
            e.printStackTrace();

        }

        return conn;
    }
}
