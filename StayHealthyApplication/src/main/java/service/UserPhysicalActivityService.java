package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DatabaseFunctions;

public class UserPhysicalActivityService {

	public static List<dto.PhysicalActivityDto> GetPhysicalActivityList(String Keyword) {

		List<dto.PhysicalActivityDto> list = new ArrayList<dto.PhysicalActivityDto>();

		try {
			ResultSet rs = DatabaseFunctions.GetPhysicalActivityList(Keyword);

			while (rs.next()) {
				dto.PhysicalActivityDto dto = new dto.PhysicalActivityDto();
				dto.Id = Integer.parseInt(rs.getString("Id"));
				dto.Code = Integer.parseInt(rs.getString("Code"));
				dto.Mets = Double.parseDouble(rs.getString("Mets"));
				dto.MajorHeading = rs.getString("MajorHeading");
				dto.SpecificActivities = rs.getString("SpecificActivities");

				list.add(dto);
			}

		} catch (ClassNotFoundException e) {
			String exc = e.toString();

		} catch (SQLException e) {

		}

		return list;
	}
	
	public static List<dto.UserPhysicalActivityDto> GetRecentPhysicalActivityListOfUser(int UserId) {

		List<dto.UserPhysicalActivityDto> list = new ArrayList<dto.UserPhysicalActivityDto>();

		try {
			ResultSet rs = DatabaseFunctions.GetRecentPhysicalActivityListOfUser(UserId);

			while (rs.next()) {
				dto.UserPhysicalActivityDto dto = new dto.UserPhysicalActivityDto();
				dto.Id = Integer.parseInt(rs.getString("Id"));
				dto.Duration = Integer.parseInt(rs.getString("Duration"));
				dto.Notes = rs.getString("Notes");
				dto.PerformedDateText = rs.getString("PerformedDate");
				dto.CreateDateText = rs.getString("CreateDate");
				dto.ActivityMajorHeading = rs.getString("MajorHeading");
				dto.ActivitySpecificActivities = rs.getString("SpecificActivities");

				list.add(dto);
			}

		} catch (ClassNotFoundException e) {
			String exc = e.toString();

		} catch (SQLException e) {
			String exc = e.toString();
		}

		return list;
	}
	 
	public static Integer InsertUserPhysicalActivity(dto.UserPhysicalActivityDto userPhysicalActivityDto) {

		Integer result;

		try {
			result = DatabaseFunctions.InsertUserPhysicalActivity(userPhysicalActivityDto);
		} catch (ClassNotFoundException e) {
			result = -1;
		} catch (SQLException e) {
			result = -1;
		}

		return result;

	}

}
