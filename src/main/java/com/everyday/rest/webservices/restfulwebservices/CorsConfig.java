package com.everyday.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.maxAge(3000);
		
//		addMapping - CORS를 적용할 url의 패턴을 정의 (/** 로 모든 패턴을 가능하게 함)
//		allowedOrigins - 허용할 origin을 정의 (* 로 모든 origin을 허용, 여러개도 지정가능)
//		allowedMethods - HTTP Method를 지정 (* 로 모든 Method를 허용)
//		maxAge - 원하는 시간만큼 request를 cashing함
	}
}
