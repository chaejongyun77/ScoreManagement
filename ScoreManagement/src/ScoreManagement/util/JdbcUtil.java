package ScoreManagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil {

	public static Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver"); //모든 오류 보고(try catch x)
		
		
	// 2. DBMS 연결
		String url = "jdbc:oracle:thin:@127.0.0.1:1521/XE";
		String user = "hr";
		String password = "HR";
		
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}
	public static void close(PreparedStatement pstmt, Connection con) {
		
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {}
			try {
				if(con != null) con.close();
			} catch (SQLException e) {}
	}

}
