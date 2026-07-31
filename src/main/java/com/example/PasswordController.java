package com.example;

import java.security.MessageDigest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {

	@RequestMapping("/hashPassword")
	public String hash(@RequestParam("password") String password) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] hash = digest.digest(password.getBytes());
		StringBuilder sb = new StringBuilder();
		for (byte b : hash) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
