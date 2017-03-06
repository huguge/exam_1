package com.ezhu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 使用REST风格的Spring MVC
 * @author Huyas
 * 
 */
@Controller
@RequestMapping("/rest")
public class RestController {

	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public String get(@PathVariable("id") Integer id) {
		System.out.println("get, id = "+id);
		return "/test/hello";
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.POST)
	public String post(@PathVariable("id") Integer id) {
		System.out.println("post, id = "+id);
		return "/test/hello";
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.PUT)
	public String put(@PathVariable("id") Integer id) {
		System.out.println("put, id = "+id);
		return "/test/hello";
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		System.out.println("delete, id = "+id);
		return "/test/hello";
	}
	
	/**
	 * 页面跳转接口
	 * @url:  
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/page/{page}", method = RequestMethod.GET)
	public String page(@PathVariable("page") String page) {
		System.out.println("page = "+page);
		return "/test/"+page;
	}
	
}
