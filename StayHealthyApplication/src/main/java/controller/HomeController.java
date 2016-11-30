package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.Person;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String Home() {	 
		return "Home";
	}

}
