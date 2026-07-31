package com.example;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@RequestMapping("/loadSession")
	public String load(@RequestParam("data") String data) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(data);
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
		Object obj = in.readObject();
		return obj.toString();
	}
}
