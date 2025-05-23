package onlineExam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class ExamLogger {
	
	 public static void logExamStart(int userId) {
		 try (Connection conn = DBConnection.getConnection()) {
	            PreparedStatement ps = conn.prepareStatement("INSERT INTO exam_logs (user_id, exam_start) VALUES (?, NOW())");
	            ps.setInt(1, userId);
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("Exam started for user : " + userId + " At : " + LocalDateTime.now());
	 }

	 public static void logExamEnd(int userId) {
		 try (Connection conn = DBConnection.getConnection()) {
	            PreparedStatement ps = conn.prepareStatement("UPDATE exam_logs SET exam_end = NOW() WHERE user_id = ? AND exam_end IS NULL ORDER BY id DESC LIMIT 1"); 
	            ps.setInt(1, userId);
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("Exam ended for user " + userId + " at " + LocalDateTime.now());
	 }

}
