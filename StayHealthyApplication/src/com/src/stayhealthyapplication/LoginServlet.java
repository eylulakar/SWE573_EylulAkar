package com.src.stayhealthyapplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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

		String email = request.getParameter("Email");
		String password = request.getParameter("Password");

		String responseText = "";

		dto.UserDto userDto = Functions.GetUserDto(email, password);

		if (userDto == null) {
			responseText = "Invalid username or password.";
		} else {
			final String cookieName = "loginCookie";
		    final String cookieValue = Integer.toString(userDto.Id) + "_" + userDto.FullName; 
		    final Boolean useSecureCookie = new Boolean(false);
		    final int expiryTime = 60 * 60 * 24;  // 24h in seconds
		    final String cookiePath = "/";

		    Cookie cookie = new Cookie(cookieName, cookieValue);
		    cookie.setSecure(useSecureCookie.booleanValue());
		    cookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
		    cookie.setPath(cookiePath);  // The cookie is visible to all the pages in the directory you specify, and all the pages in that directory's subdirectories
		    response.addCookie(cookie);			
			
			responseText = "";
		}
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(responseText);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
	
	

}
