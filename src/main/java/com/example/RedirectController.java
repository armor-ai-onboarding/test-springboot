package com.example;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

	@RequestMapping("/redirect")
	public void redirect(@RequestParam("url") String url, HttpServletResponse response) throws Exception {
		response.sendRedirect(url);
	}
}
