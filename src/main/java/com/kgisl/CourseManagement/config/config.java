package com.kgisl.CourseManagement.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration //configuration methods that should be processed by the Spring container.
@EnableWebMvc // enable the default Spring MVC configuration
public class config  implements WebMvcConfigurer {

    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("http://localhost:4200")
//        .allowedOrigins("http://localhost:56116")
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*");
    }

    // interface to handle file uploads in web app
    @Bean // returns an instance of Standard servlet multipart resolver
    public MultipartResolver multipartResolver() {
      return new StandardServletMultipartResolver();
    }
    
    
}

