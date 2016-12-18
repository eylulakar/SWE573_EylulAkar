package service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import business.GeneralFunctions;
import common.FoodApiSearchResponseListItemEntity;
import data.DatabaseFunctions;
import data.FoodDatabaseFunctions;

public class UserFoodActivityService {

	public static Integer InsertFood(dto.FoodDto foodDto) {

		Integer result;

		try {
			result = FoodDatabaseFunctions.InsertFood(foodDto);
		} catch (ClassNotFoundException e) {
			result = -1;
		} catch (SQLException e) {
			result = -1;
		}

		return result;
	}

	public static Integer InsertUserFoodActivity(dto.UserFoodActivityDto userFoodActivityDto) {

		Integer result;

		try {
			result = FoodDatabaseFunctions.InsertUserFoodActivity(userFoodActivityDto);
		} catch (ClassNotFoundException e) {
			result = -1;
		} catch (SQLException e) {
			result = -1;
		}

		return result;

	}

	
	public static Integer DeleteUserFoodActivity(int id) {

		Integer result;

		try {
			result = FoodDatabaseFunctions.DeleteUserFoodActivity(id);
		} catch (ClassNotFoundException e) {
			result = -1;
		} catch (SQLException e) {
			result = -1;
		}

		return result;

	}

	
	public static List<dto.UserFoodActivityDto> GetRecentFoodActivityListOfUser(int UserId)  {

		List<dto.UserFoodActivityDto> list = new ArrayList<dto.UserFoodActivityDto>();

		try {
			ResultSet rs = FoodDatabaseFunctions.GetRecentFoodActivityListOfUser(UserId);

			while (rs.next()) {
				dto.UserFoodActivityDto dto = new dto.UserFoodActivityDto();
				dto.Id = Integer.parseInt(rs.getString("Id"));
				dto.UserId = Integer.parseInt(rs.getString("UserId"));
				dto.FoodId = Integer.parseInt(rs.getString("FoodId"));
				dto.Ndbno = rs.getString("Ndbno");
				dto.Notes = rs.getString("Notes");
				dto.MeasureLabel = rs.getString("MeasureLabel");
				dto.MeasureValue = Double.parseDouble(rs.getString("MeasureValue"));
				dto.Amount = Double.parseDouble(rs.getString("Amount"));
				dto.TotalCalories = GeneralFunctions.RoundDouble(Double.parseDouble(rs.getString("TotalCalories")), 2);
				dto.CreateDate = rs.getDate("CreateDate");
				dto.ConsumedDate = rs.getDate("ConsumedDate");

				dto.FoodName = rs.getString("FoodName");

				list.add(dto);
			}

		} catch (ClassNotFoundException e) {
			String exc = e.toString();

		} catch (SQLException e) {
			String exc = e.toString();
		}

		return list;
	}
	
	public static List<dto.UserFoodActivityDto> GetAllFoodActivityListOfUser(int UserId, Date startDate, Date endDate) {

		List<dto.UserFoodActivityDto> list = new ArrayList<dto.UserFoodActivityDto>();

		try {
			ResultSet rs = FoodDatabaseFunctions.GetAllFoodActivityListOfUser(UserId, startDate, endDate);

			while (rs.next()) {
				dto.UserFoodActivityDto dto = new dto.UserFoodActivityDto();
				dto.Id = Integer.parseInt(rs.getString("Id"));
				dto.UserId = Integer.parseInt(rs.getString("UserId"));
				dto.FoodId = Integer.parseInt(rs.getString("FoodId"));
				dto.Ndbno = rs.getString("Ndbno");
				dto.Notes = rs.getString("Notes");
				dto.MeasureLabel = rs.getString("MeasureLabel");
				dto.MeasureValue = Double.parseDouble(rs.getString("MeasureValue"));
				dto.Amount = Double.parseDouble(rs.getString("Amount"));
				dto.TotalCalories = GeneralFunctions.RoundDouble(Double.parseDouble(rs.getString("TotalCalories")), 2);
				dto.CreateDate = rs.getDate("CreateDate");
				dto.ConsumedDate = rs.getDate("ConsumedDate");

				dto.FoodName = rs.getString("FoodName");

				list.add(dto);
			}

		} catch (ClassNotFoundException e) {
			String exc = e.toString();

		} catch (SQLException e) {
			String exc = e.toString();
		}

		return list;
	}


}
