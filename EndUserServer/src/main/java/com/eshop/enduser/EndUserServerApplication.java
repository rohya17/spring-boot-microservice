package com.eshop.enduser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EndUserServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndUserServerApplication.class, args);
	}
	
}
