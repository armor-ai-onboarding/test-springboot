package com.example;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;

@RestController
public class XmlController {

	@RequestMapping("/parseXml")
	public String parse(@RequestParam("xml") String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new ByteArrayInputStream(xml.getBytes()));
		return document.getDocumentElement().getNodeName();
	}
}
