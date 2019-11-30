package com.example.demo;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		//call java -jar /home/mrcgddmy/public_html/modern-foodies-india/foodali-webapi/foodapi.jar com.example.demo.DemoApplication 
		
		// Run spring boot jar file as backgrund servicd in linux 
//		nohup java -jar /home/mrcgddmy/public_html/modern-foodies-india/foodali-webapi/fapi.jar com.example.demo.DemoApplication &
//		The & symbol, switches the program to run in the background.
//
//		The nohup utility makes the command passed as an argument run in the background even after you log out.
//		
		SpringApplication.run(DemoApplication.class, args);
		
		// yes | cp -rf /zzz/zzz/* /xxx/xxx
		// yes | cp -rf /home/mrcgddmy/public_html/modern-foodies-india/www/* /home/mrcgddmy/public_html/
		//cp -a /source/. /dest/
         //cp -a /home/mrcgddmy/public_html/modern-foodies-india/www/. /home/mrcgddmy/public_html/

	}
	//Remove basic error controller 
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.any())
	        .paths(PathSelectors.any())
	        .paths(Predicates.not(PathSelectors.regex("/error.*")))
	        .build();
	}
	
	@Bean
	public FilterRegistrationBean simpleCorsFilter() {  
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
	    CorsConfiguration config = new CorsConfiguration();  
	    config.setAllowCredentials(true); 
	    // *** URL below needs to match the Vue client URL and port ***
	    config.setAllowedOrigins(Collections.singletonList("*")); 
	    config.setAllowedMethods(Collections.singletonList("*"));  
	    config.setAllowedHeaders(Collections.singletonList("*"));  
	    source.registerCorsConfiguration("/**", config);  
	    FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);  
	    return bean;  
	}   
}
