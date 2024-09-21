package com.eshop.mailserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EshopMailServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopMailServerApplication.class, args);
	}

}
