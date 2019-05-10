package com.aadhya.cultivateart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/views")
public class HomeController {
	@RequestMapping(value="/activate",method=RequestMethod.GET)
	public String home() {
		return "index";
	}
	
    @RequestMapping("/")
    public String index() {
        return "default";
    }
	
	@RequestMapping(value="/activatedefault",method=RequestMethod.GET)
	public String activateOffer() {
		return "default";
	}
}