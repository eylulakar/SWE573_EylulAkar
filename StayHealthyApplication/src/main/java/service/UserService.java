package service;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.DatabaseFunctions;

public class UserService {

	public static dto.UserDto GetUserDto(String Email, String Password) {
		entity.User userEntity = null;

		try {
			ResultSet rs = DatabaseFunctions.GetUser(Email, Password);

			while (rs.next()) {
				userEntity = new entity.User();
				userEntity.Id = Integer.parseInt(rs.getString("Id"));
				userEntity.Email = rs.getString("Email");		
				userEntity.FullName = rs.getString("FullName");	
			}

		} catch (ClassNotFoundException e) {
			 
		} catch (SQLException e) {
			
		}
		
		dto.UserDto userDto = null;
		
		if(userEntity!=null)
		{
			userDto = new dto.UserDto();
			userDto.Email = userEntity.Email;
			userDto.FullName = userEntity.FullName;
			userDto.Id = userEntity.Id;
		}		

		return userDto;
	}
	
	public static Integer InsertUser(dto.UserDto userDto) {

		Integer result;

		try {
			result = DatabaseFunctions.InsertUser(userDto);
		} catch (ClassNotFoundException e) {
			result = -1;
		} catch (SQLException e) {
			result = -1;
		}

		return result;

	}
	
}
