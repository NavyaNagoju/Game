package onlineExam;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertUser {

	public static void main(String[] args) throws Exception {
		String username = "navya";
        String password = "navya6303";

        String hashedPassword = AuthService.hashPassword(password);
       // System.out.println("Hashed Password: " + hashedPassword);
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password_hash) VALUES (?, ?)");
        ps.setString(1, username);
        ps.setString(2, hashedPassword);
        ps.executeUpdate();

        System.out.println("User inserted successfully!");

	}

}
