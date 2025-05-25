package com.example.demo.Config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.EnableWebMvcConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig  implements WebMvcConfigurer{

	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders("http://localhost:3000")
				.allowedOriginPatterns("http://localhost:3000")
				.allowedMethods("POST","GET","DELETE","PUT")
				.allowedHeaders("*")
				.allowCredentials(true);
				
	}
	
}
