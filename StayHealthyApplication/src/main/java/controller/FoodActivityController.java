package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import business.GeneralFunctions;
import common.FoodResponseReportFoodNutrientsEntity;
import common.FoodResponseReportFoodNutrientsMeasuresEntity;
import service.FoodService;
import service.UserFoodActivityService;
import service.UserPhysicalActivityService;
import service.UserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

@Controller
public class FoodActivityController {

	@RequestMapping("/food-add")
	public String PhysicalActivityList() {
		return "FoodAdd";
	}
	
	@RequestMapping("/my-food-activities")
	public String MyFoodActivityList() {
		return "MyFoodActivityList";
	}
	
	@RequestMapping("/food-list")
	public String FoodList() {
		return "FoodList";
	}

	@RequestMapping(value = "/GetFoodSearchResultList", method = RequestMethod.GET)
	public @ResponseBody String GetFoodSearchResultList(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;

		String keyword = request.getParameter("Prefix");

		List<common.FoodApiSearchResponseListItemEntity> foodList = FoodService.GetFoodApiSearchResults(keyword);

		isSuccess = true;

		Gson gson = new Gson();
		JsonElement jsonReturnList = gson.toJsonTree(foodList);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("list", jsonReturnList);

		return myObj.toString();
	}

	@RequestMapping(value = "/GetFoodDetailsByNdbno", method = RequestMethod.GET)
	public @ResponseBody String GetFoodDetailsByNdbno(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;

		String ndbno = request.getParameter("ndbno");

		common.FoodResponseReportFoodEntity foodDetails = FoodService.GetFoodDetailsByNdbno(ndbno);

		common.FoodResponseReportFoodNutrientsEntity energyNutrientsEntity = new common.FoodResponseReportFoodNutrientsEntity();

		for (common.FoodResponseReportFoodNutrientsEntity nutrientsEntity : foodDetails.nutrients) {
			
			boolean isEnergyNutrient = nutrientsEntity.name.equals("Energy");
			
					if (isEnergyNutrient) {
				energyNutrientsEntity = nutrientsEntity;
			}
		}

		
		String nutritionalValuesHtml = "";		
		
		 nutritionalValuesHtml += "<table>";
		 
		for (common.FoodResponseReportFoodNutrientsEntity nutrientsEntity : foodDetails.nutrients) {
			nutritionalValuesHtml+= "<tr>";
			
			nutritionalValuesHtml+= "<td valign=\"top\"><b>" + nutrientsEntity.name + " (" + nutrientsEntity.group + ")</b> " + nutrientsEntity.unit + " - " + nutrientsEntity.value + "  kcal </td> <td><b>Measures:</b> ";
		
			for (common.FoodResponseReportFoodNutrientsMeasuresEntity measureEntity : nutrientsEntity.measures) {
				nutritionalValuesHtml+= "type: " + measureEntity.label + " - " + measureEntity.eqv + " - quantity: " + measureEntity.qty + " - value: " + measureEntity.value + " kcal <br/> <br/>";
			}
			nutritionalValuesHtml+= "</td>";
			nutritionalValuesHtml+= "</tr>";
			
		}

		 nutritionalValuesHtml += "</table>";
		
		isSuccess = true;

		Gson gson = new Gson();
		JsonElement jsonReturnList = gson.toJsonTree(foodDetails);
		JsonElement energyNutrientsEntityJsonResponse = gson.toJsonTree(energyNutrientsEntity);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("foodDetails", jsonReturnList);
		myObj.add("energyNutrientsEntity", energyNutrientsEntityJsonResponse);
		myObj.add("nutritionalValuesHtml", gson.toJsonTree(nutritionalValuesHtml));

		return myObj.toString();
	}

	@RequestMapping(value = "/FoodActivityAdd", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String FoodActivityAdd(HttpServletRequest request, HttpServletResponse response) {

		boolean isSuccess = true;
		String returnMessage = "";

		int userId = GetCurrentUserId(request);

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		java.util.Date dateOfActivity = new java.util.Date();
		try {
			dateOfActivity = format.parse(request.getParameter("inputFoodActivityDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date dateOfActivityFinal = new java.sql.Date(dateOfActivity.getTime());

		// ===================================================================================
		dto.FoodDto foodDto = new dto.FoodDto();

		foodDto.Ndbno = request.getParameter("inputSelectedFoodNdbno");
		foodDto.Name = request.getParameter("inputSearchFood");

		int foodAddResultId = UserFoodActivityService.InsertFood(foodDto);

		double amount = Double.parseDouble(request.getParameter("inputAmount"));
		double measureValue = Double.parseDouble(request.getParameter("selectMeasuresList"));

		double totalCalories = amount * measureValue;

		dto.UserFoodActivityDto userFoodActivityDto = new dto.UserFoodActivityDto();

		userFoodActivityDto.Ndbno = request.getParameter("inputSelectedFoodNdbno");
		userFoodActivityDto.FoodId = foodAddResultId;
		userFoodActivityDto.UserId = userId;
		userFoodActivityDto.MeasureLabel = request.getParameter("inputMeasureLabel");
		userFoodActivityDto.MeasureValue = measureValue;
		userFoodActivityDto.UserId = userId;
		userFoodActivityDto.Amount = amount;
		userFoodActivityDto.TotalCalories = (int) totalCalories;
		userFoodActivityDto.ConsumedDate = dateOfActivityFinal;
		userFoodActivityDto.Notes = request.getParameter("inputFoodActivityNotes");

		int resultId = UserFoodActivityService.InsertUserFoodActivity(userFoodActivityDto);

		if (resultId > 0) {
			returnMessage = "Food activity added.";
			isSuccess = true;
		} else {
			returnMessage = "Error occured during process. Please try again later.";
			isSuccess = false;
		}

		// ===================================================================================

		Gson gson = new Gson();
		JsonElement jsonReturnMessage = gson.toJsonTree(returnMessage);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("message", jsonReturnMessage);

		return myObj.toString();
	}

	
	@RequestMapping(value = "/GetRecentFoodActivityList", method = RequestMethod.GET, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String GetRecentFoodActivityList(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;

		int userId = GetCurrentUserId(request);
			
		List<dto.UserFoodActivityDto> userPhysicalActivityDtoList = UserFoodActivityService.GetRecentFoodActivityListOfUser(userId);
		isSuccess = true;

		String listHtml = "";

		listHtml += "<table class=\"table table-hover\">";
		listHtml += "<thead><tr><th>#</th><th>Consumed Date</th><th>Food Type</th>";
		listHtml += "<th>Total Calories</th><th>Notes</th><th>Create Date</th><th>Action</th></tr></thead>";
		listHtml += "<tbody>";

		double totalCalories = 0;
		
		for (dto.UserFoodActivityDto userFoodActivityDto : userPhysicalActivityDtoList) {
			listHtml += "<tr>";
			listHtml += "<td>" + userFoodActivityDto.Id + "</td>";
			listHtml += "<td>" + userFoodActivityDto.ConsumedDate + "</td>";
			listHtml += "<td>" + userFoodActivityDto.FoodName + "<br/>";
			listHtml += "Measure Values: " + userFoodActivityDto.MeasureLabel + "/" + userFoodActivityDto.MeasureValue
							  + " - Amount:" + userFoodActivityDto.Amount + "</td>";
			listHtml += "<td>" + userFoodActivityDto.TotalCalories + " kcal</td>";
			listHtml += "<td>" + userFoodActivityDto.Notes + "</td>";
			listHtml += "<td>" + userFoodActivityDto.CreateDate + "</td>";		
			listHtml += "<td><button onclick=\"DeleteUserFoodActivity('" +  userFoodActivityDto.Id + "');\" "
					+ " style=\"border-color: #f89406 !important;background-color: #f89406 !important;\"  type=\"button\" class=\"btn btn-sm btn-default\">Delete</button></td>";
			listHtml += "<tr/>";
			
			totalCalories = totalCalories + userFoodActivityDto.TotalCalories;
		}
		
		totalCalories = GeneralFunctions.RoundDouble(totalCalories, 2);

		listHtml += "</tbody></table>";

		Gson gson = new Gson();
		JsonElement jsonReturnList = gson.toJsonTree(listHtml);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("list", jsonReturnList);
		myObj.add("totalCalories", gson.toJsonTree(totalCalories));

		return myObj.toString();
	}
 
	@RequestMapping(value = "/GetAllFoodActivityListOfUser", method = RequestMethod.GET, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String GetAllFoodActivityListOfUser(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;

		int userId = GetCurrentUserId(request);


		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		java.util.Date startDateOfActivity = new java.util.Date();
		try {
			startDateOfActivity = format.parse(request.getParameter("inputStartDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date startDateOfActivityFinal = new java.sql.Date(startDateOfActivity.getTime());
		
		java.util.Date endDateOfActivity = new java.util.Date();
		try {
			endDateOfActivity = format.parse(request.getParameter("inputEndDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date endDateOfActivityFinal = new java.sql.Date(endDateOfActivity.getTime());
		
		double totalCalories = 0;
		
		List<dto.UserFoodActivityDto> userPhysicalActivityDtoList = UserFoodActivityService.GetAllFoodActivityListOfUser(userId, startDateOfActivityFinal, endDateOfActivityFinal);
		isSuccess = true;

		String listHtml = "";

		listHtml += "<table class=\"table table-hover\">";
		listHtml += "<thead><tr><th>#</th><th>Consumed Date</th><th>Food Type</th>";
		listHtml += "<th>Total Calories</th><th>Notes</th><th>Create Date</th></tr></thead>";
		listHtml += "<tbody>";

		for (dto.UserFoodActivityDto userFoodActivityDto : userPhysicalActivityDtoList) {
			listHtml += "<tr>";
			listHtml += "<td>" + userFoodActivityDto.Id + "</td>";
			listHtml += "<td>" + userFoodActivityDto.ConsumedDate + "</td>";
			listHtml += "<td>" + userFoodActivityDto.FoodName + "<br/>";
			listHtml += "Measure Values: " + userFoodActivityDto.MeasureLabel + "/" + userFoodActivityDto.MeasureValue
							  + " - Amount:" + userFoodActivityDto.Amount + "</td>";
			listHtml += "<td>" + userFoodActivityDto.TotalCalories + " kcal</td>";
			listHtml += "<td>" + userFoodActivityDto.Notes + "</td>";
			listHtml += "<td>" + userFoodActivityDto.CreateDate + "</td>";
			listHtml += "<td><button onclick=\"DeleteUserFoodActivity('" +  userFoodActivityDto.Id + "');\" "
					+ " style=\"border-color: #f89406 !important;background-color: #f89406 !important;\"  type=\"button\" class=\"btn btn-sm btn-default\">Delete</button></td>";
			listHtml += "<tr/>";		 
			
			totalCalories = totalCalories + userFoodActivityDto.TotalCalories;
		}
		
		totalCalories = GeneralFunctions.RoundDouble(totalCalories, 2);		

		listHtml += "</tbody></table>";

		Gson gson = new Gson();
		JsonElement jsonReturnList = gson.toJsonTree(listHtml);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("list", jsonReturnList);
		myObj.add("totalCalories",  gson.toJsonTree(totalCalories));
		
		return myObj.toString();
	}
	
	@RequestMapping(value = "/DeleteUserFoodActivity", method = RequestMethod.GET, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String DeleteUserFoodActivity(HttpServletRequest request, HttpServletResponse response) {
		
		boolean isSuccess = true;
		String returnMessage = "";
		
		int userFoodActivityId = Integer.parseInt(request.getParameter("userFoodActivityId"));

		int resultId = UserFoodActivityService.DeleteUserFoodActivity(userFoodActivityId);
	 

		if (resultId > 0) {
			returnMessage = "Food activity was deleted successfully.";
			isSuccess = true;
		} else {
			returnMessage = "Error occured during process. Please try again later.";
			isSuccess = false;
		}

		
		Gson gson = new Gson();
		JsonElement jsonReturnMessage = gson.toJsonTree(returnMessage);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("message", jsonReturnMessage);

		return myObj.toString();
	}
 
	
	private int GetCurrentUserId(HttpServletRequest request) {

		int userId = 0;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String cookieName = cookies[i].getName();
				if (cookieName.equals("loginCookie")) {
					String cookieValue = "";

					try {
						cookieValue = java.net.URLDecoder.decode(cookies[i].getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String[] parts = cookieValue.split("_");
					userId = Integer.parseInt(parts[0]);
				}
			}
		}

		return userId;
	}
}
