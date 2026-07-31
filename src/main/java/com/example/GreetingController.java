package com.example;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@RequestMapping("/greeting")
	public void greeting(@RequestParam("name") String name, HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		response.getWriter().write("<html><body><h1>Hello " + name + "</h1></body></html>");
	}
}
