package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.FoodApiSearchResponseListItemEntity;
import data.DatabaseFunctions;
import data.FoodDatabaseFunctions;

public class FoodService {

	public static List<common.FoodApiSearchResponseListItemEntity> GetFoodApiSearchResults(String Keyword) {

		List<common.FoodApiSearchResponseListItemEntity> foodList = new ArrayList<common.FoodApiSearchResponseListItemEntity>();

		String apiResponseResult = FoodApiService.SearchFoodByKeyword(Keyword);

		TypeToken<common.FoodApiSearchResponse> token = new TypeToken<common.FoodApiSearchResponse>() {
		};

		Gson gson = new Gson();
		common.FoodApiSearchResponse apiResponseResultObject = gson.fromJson(apiResponseResult, token.getType());

		if (apiResponseResultObject != null) {
			foodList = apiResponseResultObject.list.item;
		}

		return foodList;
	}

	public static String GetFoodDetailsTextByNdbno(String ndbno) {

		String foodDetails = "";

		String apiResponseResult = FoodApiService.GetFoodDetailsTextByNdbno(ndbno);

		foodDetails = apiResponseResult;
		// TypeToken<common.FoodApiSearchResponse> token = new
		// TypeToken<common.FoodApiSearchResponse>(){};

		// Gson gson = new Gson();
		// common.FoodApiSearchResponse apiResponseResultObject =
		// gson.fromJson(apiResponseResult, token.getType());

		// if(apiResponseResultObject!=null)
		// {
		// foodList = apiResponseResultObject.list.item;
		// }

		return foodDetails;
	}

	public static common.FoodResponseReportFoodEntity GetFoodDetailsByNdbno(String ndbno) {

		common.FoodResponseReportFoodEntity foodResponse = new common.FoodResponseReportFoodEntity();

		String apiFoodResponseText = FoodApiService.GetFoodDetailsTextByNdbno(ndbno);

		TypeToken<common.FoodResponse> token = new TypeToken<common.FoodResponse>() {
		};

		Gson gson = new Gson();
		common.FoodResponse apiResponseResultObject = gson.fromJson(apiFoodResponseText, token.getType());

		if (apiResponseResultObject != null) {
			foodResponse = apiResponseResultObject.report.food;
		}

		return foodResponse;
	}


}
