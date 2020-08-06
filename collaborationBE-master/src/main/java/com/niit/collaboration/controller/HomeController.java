package com.niit.collaboration.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController 
{
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(HomeController.class);
	@CrossOrigin(origins="http://localhost:8081")
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String getIndexPage() 
	{
		Logger.debug("Start of method to list the getIndexPage");
		Logger.debug("End of method to list the getIndexPage");
		return "home";
	}
}
