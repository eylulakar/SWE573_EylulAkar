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
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//HttpSession session = request.getSession();
		//dto.UserDto userDto = (dto.UserDto)session.getAttribute("group"); 
		
		String responseText = "";
		
		 Cookie[] cookies = request.getCookies();
		  if(cookies != null) {
		      for (int i = 0; i < cookies.length; i++) {
		    	  String cookieName = cookies[i].getName();
		    	  if(cookieName.equals("loginCookie"))
		    	  {		    		  
		    		  responseText = cookies[i].getValue();
		    	  }
		      	}
		   }
		
		
	    response.setContentType("text/html");
	    request.setAttribute("todo", responseText);
	    response.getWriter().write(responseText);
	   // request.getRequestDispatcher("/index.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
