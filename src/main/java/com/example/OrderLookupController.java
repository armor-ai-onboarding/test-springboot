package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderLookupController {

	@RequestMapping("/order")
	public String lookup(@RequestParam("orderId") String orderId) throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "app");
		String query = "SELECT id, status, total FROM orders WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, orderId);
		ResultSet resultSet = statement.executeQuery();
		StringBuilder result = new StringBuilder();
		while (resultSet.next()) {
			result.append(resultSet.getString("status")).append("\n");
		}
		return result.toString();
	}
}
