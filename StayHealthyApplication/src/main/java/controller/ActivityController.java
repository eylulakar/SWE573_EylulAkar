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

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;

@Controller
public class ActivityController {

	@RequestMapping("/activity-list")
	public String ActivityList() {	 
		return "ActivityList";
	}
	
	@RequestMapping("/activity-add")
	public String ActivityAdd() {	 
		return "ActivityAdd";
	}
}
