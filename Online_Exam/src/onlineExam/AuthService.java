package onlineExam;
import java.math.BigInteger; 
import java.nio.charset.StandardCharsets; //StandardCharsets provides constants for standard character sets
import java.security.MessageDigest; //MessageDigest
import java.sql.*;
import java.time.LocalDateTime;

public class AuthService {
	
	public static String hashPassword(String password) throws Exception { 
		MessageDigest md = MessageDigest.getInstance("SHA-256"); //we can hash data, but we can't reverse it.
		
		byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8)); 
		return new BigInteger(1,hash).toString(16); 
		
	}
	
	public static int login(String username, String password) throws Exception { 
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT id, password_hash FROM users WHERE username=?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();  
		if(rs.next()) {  
			String storedHash = rs.getString("password_hash"); //to get 
			String enteredHash = hashPassword(password);
			
		/*	System.out.println("Stored Hash: " + storedHash);
	        System.out.println("Entered Hash: " + enteredHash);*/
	        
			if (storedHash.equals(enteredHash)) {
			//	System.out.println("[" + LocalDateTime.now() + "] Login successful for usr : " + username);
				int userId = rs.getInt("id");
				System.out.println("User logged in at: " + java.time.LocalDateTime.now());
				return userId; //return user ID when it is success
				
			}else {
				System.out.println("Password mismatch!");
			}
		}else {
			 System.out.println("User not found!");
		}
		return -1; //login failed
		
	}

}
