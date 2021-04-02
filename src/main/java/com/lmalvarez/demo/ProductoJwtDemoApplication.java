package com.lmalvarez.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProductoJwtDemoApplication extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ProductoJwtDemoApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(ProductoJwtDemoApplication.class, args);
	}

}
