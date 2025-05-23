package onlineExam;
import java.sql.*;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/examonline";
	private static final String USER = "root";
	private static final String PASS = "navya@4488";
	
	public static Connection getConnection() throws SQLException { // it is return type
		return DriverManager.getConnection(URL, USER, PASS);
		
		//sql-14
		
		
	}

}
