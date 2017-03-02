package com.ezhu.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezhu.domain.Person;

@Controller
@RequestMapping("/mvc")
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "/test/hello";
	}
	
	@RequestMapping("/person")
	public String toPerson(String name, double age) {
		System.err.println("name="+name+", age="+age);
		return "/test/hello";
	}
	
	@RequestMapping("/person1")
	public String toPerson(Person p) {
		System.out.println("name="+p.getName()+", age="+p.getAge());
		return "/test/hello";
	}
	
	@RequestMapping("/date")
	public String date(Date date) {
		System.out.println("date = "+date);
		return "/test/hello";
	}
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
}
