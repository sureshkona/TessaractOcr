package com.example.ocr.tessaractdemo.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration

@ComponentScan
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter    {
	
	  @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		  registry.addResourceHandler("/**")
          .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	            "classpath:/META-INF/resources/", "classpath:/resources/",
	            "classpath:static/Tessaract-Data/tessdata", "classpath:/public/" };
	  

}
