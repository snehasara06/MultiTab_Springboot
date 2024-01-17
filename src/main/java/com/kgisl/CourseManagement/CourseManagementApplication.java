package com.kgisl.CourseManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication 
// @Configuration, @EnableAutoConfiguration(dependencies), @ComponentScan(Controller, services, directives) , @SpringBootConfiguration

public class CourseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagementApplication.class, args);
//		can be written in any class
	}

//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//	    resolver.setMaxUploadSize(5242880); // Set your max upload size
//	    return resolver;
//	}
}
