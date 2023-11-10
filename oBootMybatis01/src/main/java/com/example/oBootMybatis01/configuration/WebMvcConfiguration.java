package com.example.oBootMybatis01.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.oBootMybatis01.service.SampleInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
		// InterceptorRegistry : 환경작업을 내부적으로 저장해 놓고 사용 
	public void addInterceptors(InterceptorRegistry registry) {
		// 누군가 URL로 interCeptor을 치면 SampleInterceptor() 처리해줌 
		registry.addInterceptor(new SampleInterceptor())
				.addPathPatterns("/interCeptor");
	}
}
