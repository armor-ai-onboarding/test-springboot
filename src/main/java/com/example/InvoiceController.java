package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

	@RequestMapping("/invoice")
	public String get(@RequestParam("customer") String customer, @RequestParam("year") String year) throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "app");
		Statement statement = connection.createStatement();
		String query = "SELECT id, amount FROM invoices WHERE customer_name = '" + customer + "' AND year = " + year;
		ResultSet resultSet = statement.executeQuery(query);
		StringBuilder result = new StringBuilder();
		while (resultSet.next()) {
			result.append(resultSet.getString("amount")).append("\n");
		}
		return result.toString();
	}
}
