package onlineExam;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) throws Exception {
		 SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
		 String password = "navya6303";
		 String hash = AuthService.hashPassword(password); //to get correct hash I used this
		 System.out.println("Hash for navya6303: " + hash);
		
	}

}
