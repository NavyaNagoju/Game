package onlineExam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class AnswerService {
	public static void saveAnswer(int userId, int questionId, char answer) {
		//System.out.println("Answer saved: User " + userId + "Question" + questionId + "Answer:" + answer);
		LocalDateTime time = LocalDateTime.now();
		System.out.println("Answer saved at : " + time + " | User : " + userId +",Question : " + questionId + ", Answer : " + answer);
		
		try(Connection conn = DBConnection.getConnection()){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO user_answers (user_id, question_id, selected_option, timestamp) VALUES (?, ?, ?, NOW())");
	            ps.setInt(1, userId);
	            ps.setInt(2, questionId);
	            ps.setString(3, String.valueOf(answer));
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

}
