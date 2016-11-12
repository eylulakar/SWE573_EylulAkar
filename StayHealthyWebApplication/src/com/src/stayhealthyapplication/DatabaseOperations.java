package com.src.stayhealthyapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperations {
	private static String connectionUrl = "jdbc:mysql://stayhealthydatabase.chrawdvr4cn2.us-west-2.rds.amazonaws.com:3306";
	private static String connectionUser = "stayhealthyuser";
	private static String connectionPassword = "pkrj0953-2";
	
	private static String connectionString = "jdbc:sqlserver://sql.doganli.com;user=eylulakar_01;password=eyl33BBx;database=eylulakar_01";
	private static String classForname = "com.mysql.jdbc.Driver";

	public static ResultSet GetComplaintsList() throws SQLException, ClassNotFoundException {
		Class.forName(classForname);
		Connection conn = DriverManager.getConnection(connectionString);	
		Statement sta = conn.createStatement();

		String selectSql = "";
		selectSql += "SELECT Complaint.*, ComplaintType.Name AS ComplaintTypeName ";
		selectSql += "FROM [513_Complaint] AS Complaint ";
		selectSql += "INNER JOIN [513_ComplaintType] AS ComplaintType ON ComplaintType.ComplaintTypeID = Complaint.ComplaintTypeID ";
		selectSql += "ORDER BY Complaint.CreateDate DESC";
		
		ResultSet rs = sta.executeQuery(selectSql);
		return rs;
	}

	public static ResultSet GetUser(String Email, String Password) throws SQLException, ClassNotFoundException {
		Class.forName(classForname);
		Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);		
		
		String selectSql = "";
		selectSql += "SELECT User.* ";
		selectSql += "FROM StayHealthyDB.User AS User  ";
		selectSql += "WHERE (User.Email = ? AND User.Password = ?) ";
 	
		PreparedStatement sta = conn.prepareStatement(selectSql);
		sta.setString(1, Email);
		sta.setString(2, Password);
		 
		ResultSet rs = sta.executeQuery();
		return rs;
	}

	
	public static Integer InsertComplaint(String FlightNumber, String FlightDetails, String FullName, String Phone,
			String Email, String Type, String Subject, String MessageText, String IpAddress)
			throws SQLException, ClassNotFoundException {

		Class.forName(classForname);
		Connection conn = DriverManager.getConnection(connectionString);

		String insertStatement = "INSERT INTO [513_Complaint] ([FlightNumber],[FlightDetails],[FullName],[Phone],[Email],[ComplaintTypeID],[Subject],[MessageText],[IpAddress]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) SELECT SCOPE_IDENTITY()";

		PreparedStatement statement = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, FlightNumber);
		statement.setString(2, FlightDetails);
		statement.setString(3, FullName);
		statement.setString(4, Phone);
		statement.setString(5, Email);
		statement.setString(6, Type);
		statement.setString(7, Subject);
		statement.setString(8, MessageText);
		statement.setString(9, IpAddress);
		int result = statement.executeUpdate();
	
		return result;

	}

}