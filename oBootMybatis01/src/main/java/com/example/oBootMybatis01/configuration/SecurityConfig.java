package com.example.oBootMybatis01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	// 암호화 모듈 추가 이유 : 스프링 시큐리티를 활용하기 위해서는 DB에 해쉬로 암호화된 패스워드를 저장
	@Bean
	public BCryptPasswordEncoder encoderPwd() {
		return new BCryptPasswordEncoder();
	}
	
	// CSRF : 자신의 의지와는 무관하게 공격자가 의도한 행위(수정, 삭제, 등록등)를 특정 웹사이트에 요청하게 하는 공격
	// authorizeRequests() : 인증, 인가
	// anyRequest() : 어떤 request도 허용해줌
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();
		
		return http.build();
	}
}
