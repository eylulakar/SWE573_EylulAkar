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

import model.Person;
import model.UserModel;
import service.UserService;

@Controller
public class UserController {

	@RequestMapping("/login")
	public String person() {
		return "login";
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
}
