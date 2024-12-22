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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@CrossOrigin("*")
@RestController("ProductController")
@RequestMapping("/products")
public class ProductController {
	
	@Autowired ProductService productService;

	@Operation(summary = "Get all products with given search filters", 
			description = "gets all products where search tag word matches with search filters of products")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "All products matching search tags"),
	    })
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllProducts( 
			@RequestParam( required = false ) Integer page,
			@RequestParam( required = false ) Integer pageSize,
			@RequestParam( required = false ) String searchTags,
			@RequestParam( required = false ) Integer shopId) {
		
		page = page == null ? 0 : page;
		pageSize = pageSize == null ? 10 : pageSize;
		
		return productService.getAllProducts(searchTags,shopId, PageRequest.of(page, pageSize));
	}
	
	@Operation(summary = "Get all products with given product ids", 
			description = "gets all products with given product ids ")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "All products having product ids provided in list."),
	        @ApiResponse(responseCode = "404", description = "Product not found with given Ids."),
	    })
	@GetMapping("/getByIds")
	public ResponseEntity<Object> getAllProductsByIds( 
			@Valid @NotNull(message = "Product id list is mandatory.") 
			@RequestParam(required = true) List<Integer> productIds ) {
		return productService.getProductsByIds(productIds);
	}
	
	@Operation(summary = "Saves product details.", 
			description = "Saves product details and product photo.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "400", description = "Product image is mandatory to add product."),
	        @ApiResponse(responseCode = "200", description = "Product details saved successfully."),
	    })
	@PostMapping(value="/save")
	public ResponseEntity<Object> saveProductDetails( @Valid @RequestBody Product product,
			@Valid @NotNull(message = "Product image can not be null") 
			@RequestParam MultipartFile productImage ) throws IOException {
		return productService.saveProductDetails( product, productImage );
	}
	
	@Operation(summary = "Updates product availability", 
			description = "Updates product availability for given product id's.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Product availability changed for x products")
	    })
	@PutMapping("/changeAvailability")
	public ResponseEntity<Object> changeProductsAvailabilitlity( @Valid @RequestBody Map<Integer,Boolean> productsAvailability ){
		return productService.updateProductAvailability( productsAvailability );
	}
	
	@Operation(summary = "Marks product as deleted", 
			description = "Do not deletes the product but marks product as deleted")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "x products deleted successfully.")
	    })
	// soft delete
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProducts( @Valid @NotBlank @RequestParam(required=true) String productIds ){
		return productService.deleteProducts( productIds );
	}
	
	@Operation(summary = "Checks if products are valid for given list of ids.", 
			description = "Checks if products exists with for given list of product ids, "
					+ "if exists checks if it is marked deleted or not, if marked deleted will consider as not valid")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "boolean value - true"),
	        @ApiResponse(responseCode = "200", description = "These ids are not valid : x,y,z")
	    })
	@GetMapping("/checkForValidProducts")
	public ResponseEntity<Object> areProductsValid( @RequestBody List<Integer> productIds ){
		return productService.areProductsValidForCart( productIds );
	}
}
