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

import model.Person;

@Controller
public class PersonController {

	@RequestMapping("/person")
	public String person(Model model) {

		Person p = new Person();

		p.setFirstname("eylül");
		p.setLastname("akar");
		p.setAge(28);
		model.addAttribute("person", p);
		return "personview";
	}

	//@ResponseBody
	//@RequestMapping("/")
	//String Entry() {
	//	return "My spring boottttt app";
	//}

 

}
