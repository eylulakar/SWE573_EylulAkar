package com.src.stayhealthyapplication;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.catalina.connector.Request;


import dto.UserDto;

public class Functions {

	public static Integer GetUser(String Email, String Password) {
		Integer result = 0;

		try {
			ResultSet rs = DatabaseOperations.GetUser(Email, Password);

			while (rs.next()) {
				String userId = rs.getString("Id");
				result = Integer.parseInt(userId);
			}

		} catch (ClassNotFoundException e) {
			result = -1;
		} catch (SQLException e) {
			result = -1;
		}

		return result;
	}
 
	public static dto.UserDto GetUserDto(String Email, String Password) {
		dto.UserDto userDto = null;

		try {
			ResultSet rs = DatabaseOperations.GetUser(Email, Password);

			while (rs.next()) {
				userDto = new dto.UserDto();
				userDto.Id = Integer.parseInt(rs.getString("Id"));
				userDto.Email = rs.getString("Email");		
				userDto.FullName = rs.getString("FullName");	
			}

		} catch (ClassNotFoundException e) {
			 
		} catch (SQLException e) {
			
		}

		return userDto;
	}
	
	public static Integer InsertUser(UserDto userDto) {

		Integer result;

		try {
			result = DatabaseOperations.InsertUser(userDto);
		} catch (ClassNotFoundException e) {
			result = -1;
		} catch (SQLException e) {
			result = -1;
		}

		return result;

	}
 

}
