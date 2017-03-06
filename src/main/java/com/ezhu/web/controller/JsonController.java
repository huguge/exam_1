package com.ezhu.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezhu.domain.User;

/**
 * 返回json格式的字符串
 * @author Huyas
 *
 */
@Controller
@RequestMapping("/json")
public class JsonController {

	@ResponseBody
	@RequestMapping("/user")
	public User getUser() {
		User u = new User();
		u.setId(1);
		u.setName("name");
		u.setBirth(new Date());
		return u;
	}
	
}
