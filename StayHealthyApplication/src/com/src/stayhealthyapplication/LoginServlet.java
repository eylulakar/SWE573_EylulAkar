package com.src.stayhealthyapplication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String returnMessage = "";
		Boolean isSuccess = false;
		
		String email = request.getParameter("formLoginEmail");
		String password = request.getParameter("formLoginPassword");


		dto.UserDto userDto = Functions.GetUserDto(email, password);

		if (userDto == null) {
			returnMessage = "Invalid username or password.";
			isSuccess = false;
		} else {
			String cookieName = "loginCookie";		  
		    int expiryTime = 60 * 60 * 24;  // 24h in seconds
		    String cookiePath = "/";
		    Boolean useSecureCookie = new Boolean(false);
		    
		    String cookieValue = Integer.toString(userDto.Id) + "_" + userDto.FullName; 		  
		    cookieValue = java.net.URLEncoder.encode(cookieValue, "UTF-8");		    
		    
		    Cookie cookie = new Cookie(cookieName, cookieValue);
		    cookie.setSecure(useSecureCookie.booleanValue());
		    cookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
		    cookie.setPath(cookiePath);  // The cookie is visible to all the pages in the directory you specify, and all the pages in that directory's subdirectories
		    response.addCookie(cookie);			
			
		    returnMessage = "";			
			isSuccess = true;
		}		
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		Gson gson = new Gson();
		JsonElement jsonReturnMessage = gson.toJsonTree(returnMessage);

		JsonObject myObj = new JsonObject();

		myObj.addProperty("success", isSuccess);
		myObj.add("message", jsonReturnMessage);

		out.println(myObj.toString());

		out.close();

	}
	
	

}
