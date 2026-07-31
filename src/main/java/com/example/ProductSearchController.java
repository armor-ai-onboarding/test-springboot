package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductSearchController {

	@RequestMapping("/productSearch")
	public String search(@RequestParam("category") String category) throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "app");
		Statement statement = connection.createStatement();
		String query = "SELECT id, name, price FROM products WHERE category = '" + category + "'";
		ResultSet resultSet = statement.executeQuery(query);
		StringBuilder result = new StringBuilder();
		while (resultSet.next()) {
			result.append(resultSet.getString("name")).append("\n");
		}
		return result.toString();
	}
}
