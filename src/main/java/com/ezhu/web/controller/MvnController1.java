package com.ezhu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class MvnController1 {

	/**
	 * 使用@RequestParam注解指定参数的name
	 * @url: http://localhost/exam_1/test/param?id=1&name=yyy
	 * @output: id = 1, name = yyy
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/param")
	public String testRequestMapping(@RequestParam(value="id") Integer id, 
			@RequestParam(value="name") String name) {
		System.out.println("id = "+id+", name = "+ name);
		return "/test/hello";
	}
	
}
