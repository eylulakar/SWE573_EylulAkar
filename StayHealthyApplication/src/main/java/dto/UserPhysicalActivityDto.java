package dto;

import java.sql.Date;

public class UserPhysicalActivityDto {
	public int Id;
	public int UserId;
	public int PhysicalActivityId;
	public int Duration;
	public Date PerformedDate;	
	public String Notes;
	public Date CreateDate;
	public Double TotalCalories;
	
	public String CreateDateText;	
	public String PerformedDateText;
	public String ActivitySpecificActivities;
	public String ActivityMajorHeading;
	
}
