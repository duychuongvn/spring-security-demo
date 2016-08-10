package com.github.hvcntt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("index");
		return model;

	}

	@RequestMapping(value = { "/login"}, method = RequestMethod.GET)
	public ModelAndView loginPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Login");
		model.addObject("message", "This is login page!");
		model.setViewName("login");
		return model;

	}



	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		System.out.println("======== Loading adminPage()");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is admin page!");
		model.setViewName("admin");

		return model;

	}
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView homePage() {

		System.out.println("======== Loading userPage()");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is main page!");
		model.setViewName("main");

		return model;

	}
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView helloPage() {

		System.out.println("======== Loading helloPage()");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is user page!");
		model.setViewName("user");

		return model;

	}
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String deniedPage() {
		return "denied";

	}
}