package com.eshop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
	
	private static final String[] END_USER_APP_PATHS = {"/cart/**", "/endUser/**", "/orders/**"};
	private static final String[] ESHOP_OWNER_USER_PATHS = {"/eshopOwner/**","/products/**"};

	private static final String END_USER_URI = "lb://END-USER-SERVER";
	private static final String ESHOP_OWNER_USER_URI = "lb://ESHOP-OWNER-USER-SERVER";
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			.route("path_route", r -> r
					.path( END_USER_APP_PATHS )
					.uri( END_USER_URI ))
			.route("path_route", r -> r
					.path( ESHOP_OWNER_USER_PATHS )
					.uri( ESHOP_OWNER_USER_URI ))
			.build();
	}

}
