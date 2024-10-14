package com.eshop.enduser.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ESHOP-OWNER-USER-SERVER", fallback = EshopOwnerUserClientFallback.class
// url="${eshop.owner.app.url}"
)
public interface EshopOwnerUserClient {

	@GetMapping("/products/checkForValidProducts")
	ResponseEntity<Object> checkIfProductsAreValid( @RequestBody List<Integer> productIds);

	@GetMapping("/products/getByIds")
	ResponseEntity<Object> getProducts(@RequestParam List<Integer> productIds);

}
