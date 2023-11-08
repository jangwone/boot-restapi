package com.everyday.rest.webservices.restfulwebservices.work;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
public class WorkController {
	
	@GetMapping("test")
	public String abc() {
		return "test ";
	}
	
	//Convert Json {"message" : "json message"}
	//jackson, responsebody, boot autoconfigure
	@GetMapping(path = "jsonBean")
	public JsonBean bean() {
		return new JsonBean("json message");
	}
	
}
