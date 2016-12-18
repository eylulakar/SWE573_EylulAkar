package common;

import java.util.List;

public class FoodResponseReportFoodNutrientsEntity {
	public String nutrient_id;
	public String name;
	public String group;
	public String unit;
	public String value;
	public List<FoodResponseReportFoodNutrientsMeasuresEntity> measures;
}
