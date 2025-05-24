package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	private Connection connection;
	private Scanner scanner;
	
	public Patient(Connection connection,Scanner scanner) { // this is constractor special method used to initialize an object when it's created.
		this.connection = connection;
		this.scanner = scanner;
	}
	public void addPatient() {  //method for insert data
		System.out.println("Enter Patient Name : ");
		String name = scanner.next(); 
		System.out.println("Enter Patient Age : ");
		int age = scanner.nextInt();
		System.out.println("Enter Patient Gender : ");
		String gender = scanner.next(); 
		
		try {
			String query = "INSERT INTO patients(name , age , gender) VALUES (? , ? ,? )";   //? mark will be as a placeholder
			PreparedStatement preparedStatement = connection.prepareStatement(query);  
			//PreparedStatement is an interface in the java.sql package that represents a precompiled SQL statement.
			//which represents a precompiled SQL query with placeholders (such as ?).
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, gender);
			int affectedRows = preparedStatement.executeUpdate(); 
			//executeUpdate():- This method is used when you want to execute an SQL statement that doesn't return data, but instead performs an update on the database.
			//Java is used to execute an SQL statement that modifies the database (like an INSERT, UPDATE, or DELETE query).
			//The executeUpdate() method returns the number of rows affected by the SQL operation.
			if(affectedRows>0) {
				System.out.println("Patient Added Successfully ");
			}else {
				System.out.println("Failed To Add Patient");
			}
			
			}catch (SQLException e) {
				e.printStackTrace(); 
				//The printStackTrace() method is a helpful tool for printing detailed exception information to the console, 
				//allowing to trace the exact sequence of events that led to an error in their application
			}
	}
	public void viewPatients() {  //method for read operation
		String query = "select * from patients";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query); 
			ResultSet resultset = preparedStatement.executeQuery(); //the database returns a ResultSet object, which contains the rows and columns of the query result.
			System.out.println("Patients : ");
			System.out.println("+-------------+-------------------+-------+----------+");
			System.out.println("| Patients Id | Name              | Age   | Gender   |");
			System.out.println("+-------------+-------------------+-------+----------+");
			while(resultset.next()) {
				int id = resultset.getInt("id"); //In getInt() method should write the name in sql database "id" this is taken from database
				String name = resultset.getString("name");
				int age = resultset.getInt("age");
				String gender = resultset.getString("gender");
				System.out.printf("| %-11s | %-17s | %-5s | %-8s |\n", id, name, age, gender); //It id "printf" in placeholder i want to leave 13spaces so substract from % and mention letter 's' for string if it is int- %d for float-%f etc
				//These placeholders start with a percent sign (%), followed by a character that indicates the type of value to format.
				System.out.println("+-------------+-------------------+-------+----------+");
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace(); 
		}
		
	}
	public boolean getPatientById(int id) {
		String query = "SELECT * FROM patients WHERE id = ?";
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
