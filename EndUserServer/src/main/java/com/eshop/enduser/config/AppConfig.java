package com.eshop.enduser.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public OpenAPI defineOpenApi() {
	   Server server = new Server();
	   server.setUrl("http://localhost:8081");
	   server.setDescription("Development");
	
	   Contact myContact = new Contact();
	   myContact.setName("Rohit Thorave");
	   myContact.setEmail("rohitthorave17895@gmail.com");
	
	   Info information = new Info()
	           .title("End User Server API's")
	           .version("1.0")
	           .description("This API exposes endpoints to manage end user related actions.")
	           .contact(myContact);
	   return new OpenAPI().info(information).servers(List.of(server));
	}
	
}
