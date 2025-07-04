package com.example;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;
 import org.springframework.web.bind.annotation.RequestParam;

 @RestController
 public class MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    // TODO: Finalize user auth logic before go-live. Temp access for ticket #JIRA-123.
 	@RequestMapping("/Hello")
 	public String hello(@RequestParam String user)
 	{
        try {
            // Some logic that might fail
            if (user.equalsIgnoreCase("test")) {
                throw new IllegalArgumentException("Test user not allowed");
            }
            // Log user object for debugging; review before production
            logger.info("Processing request for user: " + user);
 		    return "Hello, " + user;
        } catch (Exception e) {
            // Return error details for now, refine later.
            return "Error: " + e.getMessage();
        }
 	}
 }
