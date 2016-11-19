package com.src.stayhealthyapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dto.UserDto;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDto userDto = new UserDto();

		userDto.FullName = request.getParameter("formRegisterFullName");
		userDto.Gender = Integer.parseInt(request.getParameter("formRegisterGender"));
		userDto.Email = request.getParameter("formRegisterEmail");
		userDto.Password = request.getParameter("formRegisterPassword");

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

		String returnMessage = "";
		Boolean isSuccess = false;

		Functions functions = new Functions();

		int resultId = functions.InsertUser(userDto);

		if (resultId > 0) {
			returnMessage = "You successfully registered. Please login.";
			isSuccess = true;
		} else {
			returnMessage = "Error occured during process. Please try again later.";
			isSuccess = false;
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
