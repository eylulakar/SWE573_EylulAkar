package service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DatabaseFunctions;

public class PhysicalActivityService {
	
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
	
	public static dto.PhysicalActivityDto GetPhysicalActivityDtoById(int Id) {
		
		dto.PhysicalActivityDto dto = new dto.PhysicalActivityDto();

		try {
			ResultSet rs = DatabaseFunctions.GetPhysicalActivityById(Id);

			while (rs.next()) {
			
				dto.Id = Integer.parseInt(rs.getString("Id"));
				dto.Code = Integer.parseInt(rs.getString("Code"));
				dto.Mets = Double.parseDouble(rs.getString("Mets"));
				dto.MajorHeading = rs.getString("MajorHeading");
				dto.SpecificActivities = rs.getString("SpecificActivities");
			}

		} catch (ClassNotFoundException e) {
			 
		} catch (SQLException e) {
			
		}
		
		return dto;
	}
	

}
