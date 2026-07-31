package com.example;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

	@RequestMapping("/file")
	public String read(@RequestParam("path") String path) throws Exception {
		byte[] bytes = Files.readAllBytes(Paths.get("/var/data/" + path));
		return new String(bytes);
	}
}
