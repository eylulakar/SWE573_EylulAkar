package controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import business.GeneralFunctions;
import model.Person;
import model.UserModel;
import service.UserPhysicalActivityService;
import service.UserService;

@Controller
public class UserController {

	@RequestMapping("/login")
	public String person() {
		return "Login";
	}

	@RequestMapping(value = "/LoginProcess", method = RequestMethod.GET, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String LoginProcess(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;
		String returnMessage = "";

		String email = request.getParameter("formLoginEmail");
		String password = request.getParameter("formLoginPassword");

		dto.UserDto userDto = UserService.GetUserDto(email, password);

		if (userDto == null) {
			returnMessage = "Invalid username or password.";
			isSuccess = false;
		} else {
			String cookieName = "loginCookie";
			int expiryTime = 60 * 60 * 24;
			String cookiePath = "/";
			Boolean useSecureCookie = new Boolean(false);

			String cookieValue = Integer.toString(userDto.Id) + "_" + userDto.FullName;
			try {
				cookieValue = java.net.URLEncoder.encode(cookieValue, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Cookie cookie = new Cookie(cookieName, cookieValue);
			cookie.setSecure(useSecureCookie.booleanValue());
			cookie.setMaxAge(expiryTime);
			cookie.setPath(cookiePath);
			response.addCookie(cookie);

			returnMessage = "Redirecting..";
			isSuccess = true;
		}

		Gson gson = new Gson();
		JsonElement jsonReturnMessage = gson.toJsonTree(returnMessage);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("message", jsonReturnMessage);

		return myObj.toString();
	}

	@RequestMapping(value = "/RegisterProcess", method = RequestMethod.GET, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String RegisterProcess(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;
		String returnMessage = "";

		dto.UserDto userDto = new dto.UserDto();

		userDto.FullName = request.getParameter("formRegisterFullName");
		userDto.Gender = Integer.parseInt(request.getParameter("formRegisterGender"));
		userDto.Email = request.getParameter("formRegisterEmail");
		userDto.Password = request.getParameter("formRegisterPassword");
		userDto.Weight = Integer.parseInt(request.getParameter("formRegisterWeight"));
		userDto.Height = Integer.parseInt(request.getParameter("formRegisterHeight"));

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		java.util.Date dateOfBirthDate = new java.util.Date();
		try {
			dateOfBirthDate = format.parse(request.getParameter("formRegisterDateOfBirth"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		java.sql.Date dateOfBirth = new java.sql.Date(dateOfBirthDate.getTime());
		userDto.DateOfBirth = dateOfBirth;
		
		//===============================
		// Calculate bmi value before updating user entity.
		double bmi = (double)userDto.Weight / (((double)userDto.Height / 100) * ((double)userDto.Height / 100));
		bmi = GeneralFunctions.RoundDouble(bmi, 2);

		userDto.Bmi = bmi;
		//===============================

		int resultId = UserService.InsertUser(userDto);

		if (resultId > 0) {
			returnMessage = "You successfully registered. Please login.";
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

	@ResponseBody
	@RequestMapping("/GetLoggedInUserInfo")
	String GetLoggedInUserInfo(HttpServletRequest request) {
		String responseText = "";

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
					responseText = parts[1];
				}
			}
		}

		return responseText;
	}

	@RequestMapping(value = "/GetUserProfileInfo", method = RequestMethod.GET)
	public @ResponseBody String GetUserProfileInfo(HttpServletRequest request, HttpServletResponse response) {

		boolean isSuccess = true;
		String returnMessage = "";

		int userId = GetCurrentUserId(request);

		dto.UserDto userDto = UserService.GetUserDtoById(userId);
		double bmi = userDto.Bmi;

		String bmiHtml = "";

		if (bmi < 18.5) {
			bmiHtml = "<div class=\"alert alert-black\"><h4>BMI: " + String.valueOf(bmi) + "</h4> <span>Under ideal range, you might wanna start gaining weight.</span> </div>";
		} else if (bmi >= 18.5 && bmi <23) {
			bmiHtml = "<div class=\"alert alert-success\"><h4>BMI: " + String.valueOf(bmi) + "</h4> <span>Healthy range, keep doing what you're doing!</span> </div>";
		} else if (bmi >= 23 && bmi <= 27.5) {
			bmiHtml = "<div class=\"alert alert-warning\"><h4>BMI: " + String.valueOf(bmi) + "</h4> <span>Above normal, you might wanna start losing weight!</span> </div>";
		} else if (bmi > 27.5) {
			bmiHtml = "<div class=\"alert alert-danger\"><h4>BMI: " + String.valueOf(bmi) + "</h4> <span>Very high, you might wanna get professional help and start losing weight.</span> </div>";
		}
		
		Gson gson = new Gson();
		JsonElement jsonReturnMessage = gson.toJsonTree(returnMessage);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("message", jsonReturnMessage);
		myObj.add("userDto", gson.toJsonTree(userDto));
		myObj.add("bmiHtml", gson.toJsonTree(bmiHtml));
		
		return myObj.toString();
	}
	
	
	@RequestMapping(value = "/UpdateUserInformations", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String UpdateUserInformations(HttpServletRequest request, HttpServletResponse response) {

		boolean isSuccess = true;
		String returnMessage = "";

		int userId = GetCurrentUserId(request);

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		java.util.Date dateOfBirth = new java.util.Date();
		try {
			dateOfBirth = format.parse(request.getParameter("inputDateOfBirth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date dateOfBirthFinal = new java.sql.Date(dateOfBirth.getTime());

		dto.UserDto userDto = new dto.UserDto();
	
		userDto.Id = userId;
		userDto.Weight = Integer.parseInt(request.getParameter("inputWeight"));
		userDto.Height = Integer.parseInt(request.getParameter("inputHeight"));
		userDto.Gender = Integer.parseInt(request.getParameter("selectGender"));
		userDto.Notes = request.getParameter("inputNotes");
		userDto.DateOfBirth = dateOfBirthFinal;
	
		int resultId = UserService.UpdateUser(userDto);

		if (resultId > 0) {
			returnMessage = "Your informations were updated successfully.";
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
	
	
	

	@ResponseBody
	@RequestMapping(value = "/Logout")
	String Logout(HttpServletRequest request, HttpServletResponse response) {

		String responseText = "";

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String cookieName = cookies[i].getName();
				if (cookieName.equals("loginCookie")) {
					cookies[i].setValue("");
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}

		return responseText;
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
