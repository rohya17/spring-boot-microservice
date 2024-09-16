package com.eshop.owneruser.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.common.models.Product;
import com.eshop.owneruser.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@CrossOrigin("*")
@RestController("/products")
public class ProductController {
	
	@Autowired ProductService productService;

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllProducts( 
			@RequestParam( required = false) Integer page,
			@RequestParam( required = false) Integer pageSize,
			@RequestParam( required = true) String searchTags,
			@RequestParam( required = false) Integer shopId) {
		
		page = page == null ? 0 : page;
		pageSize = pageSize == null ? 10 : pageSize;
		
		return productService.getAllProducts(searchTags,shopId, PageRequest.of(page, pageSize));
	}
	
	@GetMapping("/getByIds")
	public ResponseEntity<Object> getAllProducts( 
			@Valid @NotNull(message = "Product id list is mandatory") 
			@RequestParam(required = true) List<Integer> productIds ) {
		return productService.getProductsByIds(productIds);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveProductDetails( @Valid @RequestBody Product product,
			@Valid @NotNull(message = "Product image can not be null") 
			@RequestParam(required = true) MultipartFile productImage ) throws IOException {
		return productService.saveProductDetails( product, productImage );
	}
	
	@PutMapping("/changeAvailability")
	public ResponseEntity<Object> changeProductsAvailabilitlity( @Valid @RequestBody Map<Integer,Boolean> productsAvailability ){
		return productService.updateProductAvailability( productsAvailability );
	}
	
	// soft delete
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProducts( @Valid @NotBlank @RequestParam(required=true) String productIds ){
		return productService.deleteProducts( productIds );
	}
	
	@GetMapping("/checkForValidProducts")
	public ResponseEntity<Object> areProductsValid( @RequestBody List<Integer> productIds ){
		return productService.areProductsValidForCart( productIds );
	}
}
