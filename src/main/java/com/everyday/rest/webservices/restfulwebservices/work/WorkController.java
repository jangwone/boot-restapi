package com.everyday.rest.webservices.restfulwebservices.work;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
public class WorkController {
	
	private MessageSource messageSource;
	
	public WorkController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
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
	
	@GetMapping(path = "test/path/{name}")
	public JsonBean beanNamePathVariable(@PathVariable String name) {
		return new JsonBean(
				String.format("hello , %s", name));
	}
	
	@GetMapping("test-message")
	public String abcInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		String message = messageSource.getMessage("hello.message", null, "Defalt Message", locale);
		return message;
	}
}
