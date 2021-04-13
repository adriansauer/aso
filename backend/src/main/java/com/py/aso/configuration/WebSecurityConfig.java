package com.py.aso.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebSecurityConfig implements WebMvcConfigurer {
	private static final long MAX_AGE_SECS = 3600;

	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/**")//
		.allowedOrigins("https://localhost:3000");
	}
}
