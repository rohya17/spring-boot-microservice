package com.eshop.owneruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan({"com.eshop.owneruser.models","com.common.models"})
public class EshopOwnerUserServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopOwnerUserServerApplication.class, args);
	}

}
