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
import service.PhysicalActivityService;
import service.UserPhysicalActivityService;
import service.UserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

@Controller
public class PhysicalActivityController {

	@RequestMapping("/physical-activity-list")
	public String PhysicalActivityList() {
		return "PhysicalActivityList";
	}

	@RequestMapping("/physical-activity-add")
	public String PhysicalActivityAdd() {
		return "PhysicalActivityAdd";
	}

	@RequestMapping("/my-physical-activities")
	public String ActivityAdd() {
		return "MyPhysicalActivityList";
	}

	@RequestMapping(value = "/GetPhysicalActivityList", method = RequestMethod.GET, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String GetPhysicalActivityList(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;

		String returnMessage = "";

		List<dto.PhysicalActivityDto> activityList = UserPhysicalActivityService.GetPhysicalActivityList("");
		isSuccess = true;

		String activityListHtml = "";

		activityListHtml += "<table class=\"table table-hover\">";
		activityListHtml += "<thead><tr><th>#</th><th>Code</th><th>Mets</th><th>Description</th><th></th></tr></thead>";
		activityListHtml += "<tbody>";

		for (dto.PhysicalActivityDto physicalActivityDto : activityList) {
			activityListHtml += "<tr>";
			activityListHtml += "<td>" + physicalActivityDto.Id + "</td>";
			activityListHtml += "<td>" + physicalActivityDto.Code + "</td>";
			activityListHtml += "<td>" + physicalActivityDto.Mets + "</td>";
			activityListHtml += "<td>" + physicalActivityDto.MajorHeading + "<br/>"
					+ physicalActivityDto.SpecificActivities + "</td>";
			activityListHtml += "<td>Add</td>";
			activityListHtml += "<tr/>";
		}

		activityListHtml += "</tbody></table>";

		Gson gson = new Gson();
		JsonElement jsonReturnList = gson.toJsonTree(activityListHtml);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("list", jsonReturnList);

		return myObj.toString();
	}

	@RequestMapping(value = "/GetPhysicalActivityListOfUser", method = RequestMethod.GET, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String GetPhysicalActivityListOfUser(HttpServletRequest request,
			HttpServletResponse response) {
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

		List<dto.UserPhysicalActivityDto> userPhysicalActivityDtoList = UserPhysicalActivityService
				.GetAllPhysicalActivityListOfUser(userId, startDateOfActivityFinal, endDateOfActivityFinal);
		isSuccess = true;

		String listHtml = "";

		listHtml += "<table class=\"table table-hover\">";
		listHtml += "<thead><tr><th>#</th><th>Performed Date</th><th>Activity Type</th>";
		listHtml += "<th>Duration</th><th>Calories Burnt</th><th>Notes</th><th>Create Date</th><th>Action</th></tr></thead>";
		listHtml += "<tbody>";

		double totalCalories = 0;

		for (dto.UserPhysicalActivityDto userPhysicalActivityDto : userPhysicalActivityDtoList) {
			listHtml += "<tr>";
			listHtml += "<td>" + userPhysicalActivityDto.Id + "</td>";
			listHtml += "<td>" + userPhysicalActivityDto.PerformedDateText + "</td>";
			listHtml += "<td>" + userPhysicalActivityDto.ActivityMajorHeading + "<br/>"
					+ userPhysicalActivityDto.ActivitySpecificActivities + "</td>";
			listHtml += "<td>" + userPhysicalActivityDto.Duration + " minutes</td>";
			listHtml += "<td>" + userPhysicalActivityDto.TotalCalories + " kcal</td>";
			listHtml += "<td>" + userPhysicalActivityDto.Notes + "</td>";
			listHtml += "<td>" + userPhysicalActivityDto.CreateDateText + "</td>";
			listHtml += "<td><button onclick=\"DeleteUserPhysicalActivity('" + userPhysicalActivityDto.Id + "');\" "
					+ " style=\"border-color: #f89406 !important;background-color: #f89406 !important;\"  type=\"button\" class=\"btn btn-sm btn-default\">Delete</button></td>";
			listHtml += "<tr/>";

			totalCalories = totalCalories + userPhysicalActivityDto.TotalCalories;
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

	@RequestMapping(value = "/GetPhysicalActivityListJson", method = RequestMethod.GET)
	public @ResponseBody String GetPhysicalActivityListJson(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;

		String keyword = request.getParameter("Prefix");

		List<dto.PhysicalActivityDto> activityList = UserPhysicalActivityService.GetPhysicalActivityList(keyword);
		isSuccess = true;

		Gson gson = new Gson();
		JsonElement jsonReturnList = gson.toJsonTree(activityList);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("list", jsonReturnList);

		return myObj.toString();
	}

	@RequestMapping(value = "/CalculateTotalCalories", method = RequestMethod.GET)
	public @ResponseBody String CalculateTotalCalories(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;

		int selectedActivityId = Integer.parseInt(request.getParameter("selectedActivityId"));
		double duration = Double.parseDouble(request.getParameter("duration"));

		// ==========get user=======================
		int userId = GetCurrentUserId(request);

		dto.UserDto userDto = UserService.GetUserDtoById(userId);
		// ==========get user=======================

		// ==========get physical activity=======================
		dto.PhysicalActivityDto physicalActivityDto = PhysicalActivityService
				.GetPhysicalActivityDtoById(selectedActivityId);

		double mets = physicalActivityDto.Mets;
		// ==========get physical activity=======================

		// ==================calculate age=======================
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);

		Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(userDto.DateOfBirth);
		int birthYear = birthDate.get(Calendar.YEAR);

		int userAge = currentYear - birthYear;
		// ======================================================

		double bmr = 0;

		if (userDto.Gender == 0) {
			bmr = (9.56 * userDto.Weight) + (1.85 * userDto.Height) - (4.68 * userAge) + 655;
		} else {
			bmr = (13.75 * userDto.Weight) + (5 * userDto.Height) - (6.76 * userAge) + 66;
		}

		double t = duration / 60;

		// Calorie Burn = (BMR / 24) x MET x T
		double totalCalories = (bmr / 24) * mets * t;
		totalCalories = GeneralFunctions.RoundDouble(totalCalories, 2);

		// ===================================================
		Gson gson = new Gson();
		JsonElement jsonReturnList = gson.toJsonTree(totalCalories);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("totalCalories", jsonReturnList);

		return myObj.toString();
	}

	@RequestMapping(value = "/GetRecentPhysicalActivityList", method = RequestMethod.GET, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String GetRecentPhysicalActivityList(HttpServletRequest request,
			HttpServletResponse response) {
		boolean isSuccess = true;

		int userId = GetCurrentUserId(request);

		List<dto.UserPhysicalActivityDto> userPhysicalActivityDtoList = UserPhysicalActivityService
				.GetRecentPhysicalActivityListOfUser(userId);
		isSuccess = true;

		String listHtml = "";

		listHtml += "<table class=\"table table-hover\">";
		listHtml += "<thead><tr><th>#</th><th>Performed Date</th><th>Activity Type</th>";
		listHtml += "<th>Duration</th><th>Calories Burnt</th><th>Notes</th><th>Create Date</th><th></th>Action</tr></thead>";
		listHtml += "<tbody>";

		double totalCalories = 0;

		for (dto.UserPhysicalActivityDto userPhysicalActivityDto : userPhysicalActivityDtoList) {
			listHtml += "<tr>";
			listHtml += "<td>" + userPhysicalActivityDto.Id + "</td>";
			listHtml += "<td>" + userPhysicalActivityDto.PerformedDateText + "</td>";
			listHtml += "<td>" + userPhysicalActivityDto.ActivityMajorHeading + "<br/>"
					+ userPhysicalActivityDto.ActivitySpecificActivities + "</td>";
			listHtml += "<td>" + userPhysicalActivityDto.Duration + " minutes</td>";
			listHtml += "<td>" + userPhysicalActivityDto.TotalCalories + " kcal</td>";
			listHtml += "<td>" + userPhysicalActivityDto.Notes + "</td>";
			listHtml += "<td>" + userPhysicalActivityDto.CreateDateText + "</td>";
			listHtml += "<td><button onclick=\"DeleteUserPhysicalActivity('" + userPhysicalActivityDto.Id + "');\" "
					+ " style=\"border-color: #f89406 !important;background-color: #f89406 !important;\"  type=\"button\" class=\"btn btn-sm btn-default\">Delete</button></td>";
			listHtml += "<tr/>";

			totalCalories = totalCalories + userPhysicalActivityDto.TotalCalories;
		}

		listHtml += "</tbody></table>";

		totalCalories = GeneralFunctions.RoundDouble(totalCalories, 2);		
		
		Gson gson = new Gson();
		JsonElement jsonReturnList = gson.toJsonTree(listHtml);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("list", jsonReturnList);
		myObj.add("totalCalories", gson.toJsonTree(totalCalories));

		return myObj.toString();
	}

	@RequestMapping(value = "/PhysicalActivityAdd", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String PhysicalActivityAdd(HttpServletRequest request, HttpServletResponse response) {

		boolean isSuccess = true;
		String returnMessage = "";

		int userId = GetCurrentUserId(request);

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		java.util.Date dateOfActivity = new java.util.Date();
		try {
			dateOfActivity = format.parse(request.getParameter("inputActivityDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date dateOfActivityFinal = new java.sql.Date(dateOfActivity.getTime());

		dto.UserPhysicalActivityDto userPhysicalActivityDto = new dto.UserPhysicalActivityDto();

		userPhysicalActivityDto.PhysicalActivityId = Integer.parseInt(request.getParameter("inputSelectedActivityId"));
		userPhysicalActivityDto.UserId = userId;
		userPhysicalActivityDto.Duration = Integer.parseInt(request.getParameter("inputActivityDuration"));
		userPhysicalActivityDto.PerformedDate = dateOfActivityFinal;
		userPhysicalActivityDto.Notes = request.getParameter("inputActivityNotes");
		userPhysicalActivityDto.TotalCalories = Double.parseDouble(request.getParameter("inputTotalCalories"));

		int resultId = UserPhysicalActivityService.InsertUserPhysicalActivity(userPhysicalActivityDto);

		if (resultId > 0) {
			returnMessage = "Activity added.";
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

	@RequestMapping(value = "/DeleteUserPhysicalActivity", method = RequestMethod.GET, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String DeleteUserPhysicalActivity(HttpServletRequest request, HttpServletResponse response) {

		boolean isSuccess = true;
		String returnMessage = "";

		int userPhysicalActivityId = Integer.parseInt(request.getParameter("userPhysicalActivityId"));

		int resultId = UserPhysicalActivityService.DeleteUserPhysicalActivity(userPhysicalActivityId);

		if (resultId > 0) {
			returnMessage = "Physical activity was deleted successfully.";
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
