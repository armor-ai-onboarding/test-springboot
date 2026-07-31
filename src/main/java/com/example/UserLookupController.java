package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLookupController {

	@RequestMapping("/user")
	public String lookup(@RequestParam("name") String name) throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "app");
		Statement statement = connection.createStatement();
		String query = "SELECT id, email FROM users WHERE name = '" + name + "'";
		ResultSet resultSet = statement.executeQuery(query);
		StringBuilder result = new StringBuilder();
		while (resultSet.next()) {
			result.append(resultSet.getString("email")).append("\n");
		}
		return result.toString();
	}
}
