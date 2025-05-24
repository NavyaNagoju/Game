package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
	private Connection connection;
	
	
	public Doctor(Connection connection) { // this is constractor 
		this.connection = connection;
		
	}
	
	public void viewDoctors() {  //method for read operation
		String query = "select * from doctors";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query); 
			ResultSet resultset = preparedStatement.executeQuery(); //the database returns a ResultSet object, which contains the rows and columns of the query result.
			System.out.println("Doctors : ");
			System.out.println("+-------------+-------------------+------------------+");
			System.out.println("| Doctor Id   | Name              | Specialization   |");
			System.out.println("+-------------+-------------------+------------------+");
			while(resultset.next()) {
				int id = resultset.getInt("id"); //In getInt() method should write the name in sql database "id" this is taken from database
				String name = resultset.getString("name");
				String specialization = resultset.getString("specialization");
				System.out.printf("| %-11s | %-17s | %-16s |\n",id ,name ,specialization); //used "printf" for placeholder 
				//These placeholders start with a percent sign (%), followed by a character that indicates the type of value to format.
				System.out.println("+-------------+-------------------+------------------+");
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace(); 
		}
		
	}
	public boolean getDoctorById(int id) {
		String query = "SELECT * FROM doctors WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next()) { // if the data is exist 
				return true;
			}else {
				return false;  //if data is not exist
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
