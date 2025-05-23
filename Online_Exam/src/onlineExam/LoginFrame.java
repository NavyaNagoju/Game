package onlineExam;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
	JTextField usernameField;
	JPasswordField passwordField;
	
	public LoginFrame() { //it is constructor
		setTitle("Login");
		setSize(300, 180);
		setLocationRelativeTo(null); //This centers the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4,1)); //layout 
		
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		
		add(new JLabel("Username : "));
		add(usernameField);
		add(new JLabel("Password : "));
		add(passwordField);
		
		JButton loginBtn = new JButton("Login");
		add(loginBtn);
		
		loginBtn.addActionListener(e -> {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			
			try {
				int userId = AuthService.login(username, password);
				if (userId != -1) {
					JOptionPane.showMessageDialog(this, "Login Successfull");
					dispose(); 
					new ExamFrame(userId).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Invalid credentials");  //(loginBtn,e)
					
				}
			}catch(Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error occured");
				
			}
		});
		
	}

}
