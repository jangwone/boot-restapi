/*
package com.everyday.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 1) 모든 requests 인증
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		// 2) login page > popup
		// spring security customizer.withDefaults()
		http.httpBasic(withDefaults());
		
		// csrf 해제 - post request 401 or 403
		http.csrf().disable();
		
		return http.build();
	}
}
*/