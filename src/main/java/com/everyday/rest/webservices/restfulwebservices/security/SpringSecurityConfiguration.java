
package com.everyday.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// ** options preflight 허용
		// 기본 인증 url을 호출해서 토큰 유효성 확인
		
		// 1) 모든 requests는 인증을 받아야함.
		http.authorizeHttpRequests(
				auth -> 
					auth
					.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.anyRequest().authenticated()
				);
		// 2) login page > popup
		// spring security customizer.withDefaults()
		http.httpBasic(withDefaults());
		
		// csrf 해제 - post request 401 or 403
		//세션이 있으면 csrf 활성화 , 없으면 비활성화 stateless
		
		//상태가 없는 세션 
		http.sessionManagement(
				session -> session.sessionCreationPolicy
				(SessionCreationPolicy.STATELESS));
		
		http.csrf().disable();
		
		return http.build();
	}
}
