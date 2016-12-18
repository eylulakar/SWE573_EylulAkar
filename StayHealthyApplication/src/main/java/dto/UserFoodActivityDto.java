package dto;

import java.sql.Date;

public class UserFoodActivityDto {
	public int Id;
	public int UserId;
	public int FoodId;
	public String Ndbno;
	public String Notes;
	public String MeasureLabel;
	public double MeasureValue;
	public double Amount;
	public double TotalCalories;
	public Date ConsumedDate;
	public Date CreateDate;
	public Date ModifiedDate;
	
	public String FoodName;
}
