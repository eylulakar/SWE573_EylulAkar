package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseFunctions {
	private static String classForname = "com.mysql.jdbc.Driver";
	private static String connectionUrl = "jdbc:mysql://stayhealthydatabase.chrawdvr4cn2.us-west-2.rds.amazonaws.com:3306";
	private static String connectionUser = "stayhealthyuser";
	private static String connectionPassword = "pkrj0953-2";
	
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

	public static Integer InsertUser(dto.UserDto userDto) throws SQLException, ClassNotFoundException {

		Class.forName(classForname);
		Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);

		String sqlCommandText = "";
		sqlCommandText += "INSERT INTO StayHealthyDB.User ";
		sqlCommandText += "(FullName,Email,Password,Gender,DateOfBirth) ";
		sqlCommandText += "VALUES (?, ?, ?, ?, ?)";  // SELECT SCOPE_IDENTITY()

		PreparedStatement statement = conn.prepareStatement(sqlCommandText, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, userDto.FullName);
		statement.setString(2, userDto.Email);
		statement.setString(3, userDto.Password);
		statement.setInt(4, userDto.Gender);
		statement.setDate(5, userDto.DateOfBirth);
		
		int result = statement.executeUpdate();

		return result;

	}
}
