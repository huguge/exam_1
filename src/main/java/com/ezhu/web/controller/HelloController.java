package com.ezhu.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezhu.domain.Person;

@Controller
@RequestMapping("/mvc")
public class HelloController {

	/**
	 * @url: http://localhost/exam_1/mvc/hello
	 * 
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello() {
		return "/test/hello";
	}
	
	/**
	 * 自动匹配参数: match automatically
	 * @url: http://localhost/exam_1/mvc/person?name=hhh&age=12
	 * 
	 * @param name
	 * @param age
	 * @return
	 */
	@RequestMapping("/person")
	public String toPerson(String name, double age) {
		System.err.println("name="+name+", age="+age);
		return "/test/hello";
	}
	
	/**
	 * 自动装箱: boxing automatically
	 * @url: http://localhost/exam_1/mvc/person1?name=hhh&age=12
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping("/person1")
	public String toPerson(Person p) {
		System.out.println("name="+p.getName()+", age="+p.getAge());
		return "/test/hello";
	}
	
	/**
	 * 使用InitBinder来处理Date类型的参数
	 * the parameter was coverted in initBinder
	 * @url: http://localhost/exam_1/mvc/date?date=2017-03-02
	 * 		 http://localhost/exam_1/mvc/date?date=2017-03
	 * 		 http://localhost/exam_1/mvc/date?date=2016-05-17%2015:00:00
	 * 	三个链接日期参数值更换了的话，底下的日期格式也需要做对应替换
	 * 
	 * @param date
	 * @return
	 */
	@RequestMapping("/date")
	public String date(Date date) {
		System.out.println("date = "+date);
		return "/test/hello";
	}
	
	/**
	 * At the time of initialization, convert the type "String" to type "date"
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * 向前台传递参数 pass the parameters to front-end
	 * 前台可在Request域中取到"p"，另外使用HttpServletRequest.setAttribute()方法也可以实现向前台传递参数
	 * @url: http://localhost/exam_1/mvc/show
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/show")
	public String showPerson(Map<String, Object> map) {
		Person p = new Person();
		map.put("p", p);
		p.setAge(20);
		p.setName("Tom");
		return "/test/show";
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
	
	/**
	 * 使用Ajax调用  pass the parameters to front-end using ajax
	 * @url: http://localhost/exam_1/mvc/getPerson?name=hhh
	 * 前台页面没有用上 --> 增加了底部的page方法 --> 页面可以调用了
	 * @param name
	 * @param pw
	 */
	@RequestMapping(value = "/getPerson", method = RequestMethod.POST)
	public void getPerson(String name, PrintWriter pw) {
		pw.write("hello,"+name);
	}
	
	/**
	 * 在Controller中使用redirect方式处理请求 redirect
	 * 
	 * @return
	 */
	@RequestMapping("/redirect")
	public String redirect() {
		// 本来这里我以为是重定向到对应的页面，结果写上了/test/hello 结果报错
		// 然后改成了方法名称，正确
		return "redirect:hello";
	}
	
	/**
	 * 文件上传
	 * @url: 
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request) throws IOException {
		MultipartHttpServletRequest mhsq = (MultipartHttpServletRequest) request;
		MultipartFile file = mhsq.getFile("file");
		String fileName = file.getOriginalFilename();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 文件名是使用sdf生成时间字符串+文件后缀
		FileOutputStream fos = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+"upload/"
				+ sdf.format(new Date()) + fileName.substring(fileName.lastIndexOf(".")));
		fos.write(file.getBytes());
		fos.flush();
		fos.close();
		
		return "/test/hello";
	}
	
}
