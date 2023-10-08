package com.shyam.cartsservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//@EnableMongoRepositories
public class CartsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartsServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
