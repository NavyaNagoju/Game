package onlineExam;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;
//Exam GUI with Timer and Question

public class ExamFrame extends JFrame {
	    JLabel questionLabel; 
	    JLabel timerLabel;
	    JRadioButton[] options = new JRadioButton[4];
	    ButtonGroup group;
	    JButton nextBtn; 
	    int userId;
	    int questionIndex = 1;
	    int totalQuestions = 10; 
	    Timer timer; 
	    int timeLeft = 5*60; // 5 minutes
	    
	    public ExamFrame(int userId) { //constructer
	        this.userId = userId;
	        

	        setTitle("Exam"); 
	        setExtendedState(JFrame.MAXIMIZED_BOTH); 
	        //setSize(500, 300);
	        setLocationRelativeTo(null); // Center the window
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout()); 
	        
	        Font questionFont = new Font("Arial", Font.BOLD, 38);  
	        Font optionFont = new Font("Arial", Font.PLAIN, 35);  
	        Font buttonFont = new Font("Arial", Font.BOLD, 30);  
	        Font timerFont = new Font("Arial", Font.BOLD, 26);
	        
	        JPanel headerPanel = new JPanel(new BorderLayout());
	        questionLabel = new JLabel("Question will appear here");
	        questionLabel.setFont(questionFont);
	        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	       // add(questionLabel, BorderLayout.NORTH);
	        headerPanel.add(questionLabel, BorderLayout.WEST);
	        
	        timerLabel = new JLabel("Time Left: 05:00"); //*
	        timerLabel.setFont(timerFont);
	        timerLabel.setForeground(Color.RED);
	        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        timerPanel.add(timerLabel);
	        timerPanel.setOpaque(false);
	        headerPanel.add(timerPanel, BorderLayout.EAST);

	        add(headerPanel, BorderLayout.NORTH);  

	        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
	        group = new ButtonGroup();

	       /* for (int i = 0; i < 4; i++) {
	            options[i] = new JRadioButton("Option " + (char) ('A' + i));
	            group.add(options[i]);
	            optionsPanel.add(options[i]);
	        }*/
	            for (int i = 0; i<4; i++) { //  Prevent users from clicking “Next” without selecting an answer.
	            	options[i] = new JRadioButton("Option " + (char) ('A'+ i)); // Create new radio button labeled 
	            	options[i].setFont(optionFont); // Set font of the radio button
	            	group.add(options[i]); // Add the radio button to a button group (ensures only one option is selected at a time)
	                optionsPanel.add(options[i]);  // Add the radio button to the panel where it will be displayed
	                
	                options[i].addActionListener(e -> nextBtn.setEnabled(true)); // Enable "Next" button once an option is selected
	            }
	            
	        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
	        add(optionsPanel, BorderLayout.CENTER);

	        nextBtn = new JButton("Next");
	        nextBtn.setFont(buttonFont);
	        nextBtn.setEnabled(false); // Initially disabled till the user answer
	        add(nextBtn, BorderLayout.SOUTH);

	        nextBtn.addActionListener(e -> {
	            for (int i = 0; i < 4; i++) {
	                if (options[i].isSelected()) {
	 
	                        AnswerService.saveAnswer(userId, questionIndex, (char) ('A' + i));
	                    
	                }
	            }

	            questionIndex++;
	            if (questionIndex <= totalQuestions) {
	                loadQuestion(questionIndex);
	                nextBtn.setEnabled(false);
	            } else {
	                submitExam();
	            }
	        });

	        ExamLogger.logExamStart(userId);
	        loadQuestion(questionIndex);
	        startTimer();
	    }

	    void loadQuestion(int qid) {
	        try (Connection conn = DBConnection.getConnection()) {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM questions WHERE id=?");
	            ps.setInt(1, qid);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                questionLabel.setText(rs.getString("question"));
	                options[0].setText("A. " + rs.getString("option_a"));
	                options[1].setText("B. " + rs.getString("option_b"));
	                options[2].setText("C. " + rs.getString("option_c"));
	                options[3].setText("D. " + rs.getString("option_d"));
	                group.clearSelection();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    void startTimer() {
	        timer = new Timer();
	        timer.scheduleAtFixedRate(new TimerTask() {
	            public void run() {
	                timeLeft--;
	                SwingUtilities.invokeLater(() -> {  //Update the timerLabel on the UI thread
	                    timerLabel.setText("Time Left: " + formatTime(timeLeft));
	                });
	                
	           //     setTitle("Exam - Time left: " + formatTime(timeLeft));  //+ timeLeft + " sec");
	                if (timeLeft <= 0) {
	                    timer.cancel();
	                    submitExam();
	                }
	            }
	        }, 0, 1000); 
	    }
	    
	    String formatTime(int seconds) {
	        int mins = seconds / 60;
	        int secs = seconds % 60;
	        return String.format("%02d:%02d", mins, secs);
	    }

	    void submitExam() {
	    	if(timer != null) {
	        timer.cancel();
	    	}
	        ExamLogger.logExamEnd(userId);
	        JOptionPane.showMessageDialog(this, "Exam finished! Submitting answers.");
	        dispose();
	    }

}
