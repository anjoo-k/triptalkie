package com.walkietalkie.triptalkie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private StringToLocalDateTimeConverter stringToLocalDateTimeConverter;

	/**
	 * addFormatters 메서드 stringToLocalDateTimeConverter(년월일->DateTime 변환) 클래스 등록
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(stringToLocalDateTimeConverter);
	}

	/**
	 * addResourceHandlers 메서드 업로드 폴더를 정적리소스로 등록
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// /uploads/** 요청을 실제 파일 경로와 매핑
		registry.addResourceHandler("/uploads/**")
				.addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/");
	}
}