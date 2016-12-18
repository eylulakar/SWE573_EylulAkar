package service;

import java.sql.ResultSet;
import java.sql.SQLException;

import business.GeneralFunctions;
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

		if (userEntity != null) {
			userDto = new dto.UserDto();
			userDto.Email = userEntity.Email;
			userDto.FullName = userEntity.FullName;
			userDto.Id = userEntity.Id;
		}

		return userDto;
	}

	public static dto.UserDto GetUserDtoById(int UserId) {
		dto.UserDto userDto = new dto.UserDto();

		try {
			ResultSet rs = DatabaseFunctions.GetUserById(UserId);

			while (rs.next()) {
				userDto.Id = Integer.parseInt(rs.getString("Id"));
				userDto.Email = rs.getString("Email");
				userDto.FullName = rs.getString("FullName");
				userDto.Gender = rs.getInt("Gender");
				userDto.DateOfBirth = rs.getDate("DateOfBirth");
				userDto.Weight = rs.getInt("Weight");
				userDto.Height = rs.getInt("Height");
				userDto.Bmi = rs.getDouble("Bmi");
				userDto.Notes = rs.getString("Notes");
				userDto.DateOfBirthText = rs.getDate("DateOfBirth").toString(); //.replace(" 00:00:00.0","");
			}

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {

		}

		return userDto;
	}

	public static Integer InsertUser(dto.UserDto userDto) {

		Integer result;

		// Calculate bmi value before inserting user to database.
		double bmi = userDto.Weight / ((userDto.Height / 100) * (userDto.Height / 100));
		bmi = GeneralFunctions.RoundDouble(bmi, 2);

		try {
			result = DatabaseFunctions.InsertUser(userDto);
		} catch (ClassNotFoundException e) {
			result = -1;
		} catch (SQLException e) {
			result = -1;
		}

		return result;

	}

	public static Integer UpdateUser(dto.UserDto userDto) {

		Integer result;

		// Calculate bmi value before updating user entity.
		double bmi = (double)userDto.Weight / (((double)userDto.Height / 100) * ((double)userDto.Height / 100));
		bmi = GeneralFunctions.RoundDouble(bmi, 2);

		userDto.Bmi = bmi;
		
		try {
			result = DatabaseFunctions.UpdateUser(userDto);
		} catch (ClassNotFoundException e) {
			result = -1;
		} catch (SQLException e) {
			result = -1;
		}

		return result;

	}
}






