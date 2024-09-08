package com.eshop.owneruser.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.models.Product;
import com.eshop.owneruser.repository.ProductRepository;
import com.eshop.owneruser.utils.EshopUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired private ProductRepository productRepository;
	private static final String FILE_NAME_FORMAT = "%s - %s - %s";
	
	public ResponseEntity<Object> getAllProducts( String searchTags, Integer shopId, Pageable pageRequest ){
		
		Page<Product> allProducts = null;
		if( EshopUtils.isValidString( searchTags ) && EshopUtils.isValidInteger( shopId )  ) {
			allProducts	= productRepository.findByDeletedFalse( pageRequest );	
		}else {
			
			searchTags = EshopUtils.formatToSearchString(searchTags);
			
			/* JPA
			allProducts	= productRepository.findBySearchTagContainingAndShopId(searchTags, shopId, pageRequest );
			*/
			
			allProducts	= productRepository.findAllWithFilters(searchTags, shopId, pageRequest );
		}
		return ResponseEntity.ok(allProducts);
	}

	public ResponseEntity<Object> saveProductDetails(Product product, MultipartFile productImage) throws IOException {
		
		// check if product is new image is provided or not
		if( !EshopUtils.isValidInteger(product.getId()) && !EshopUtils.isValidFile(productImage) ) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Product image is mandatory to add product.");
		}
		
		// save image if it is provided
		if( EshopUtils.isValidFile(productImage) ) {
			String storagePath = "D:/";
			String generatedFileName = EshopUtils.generateFileName( FILE_NAME_FORMAT, productImage, product.getEshopId() );
			boolean saved = EshopUtils.storeImage(productImage, storagePath, generatedFileName );
			if(saved) {
				product.setProductImage(generatedFileName);
			}
		}
		
		// save product
		productRepository.save(product);
		return ResponseEntity.ok("Product details saved successfully.");
	}

	public ResponseEntity<Object> updateProductAvailability(Map<Integer, Boolean> productsAvailability) {
		
		int productUpdated = 0;
		Set<Integer> productIds = productsAvailability.keySet();
		for (Integer productId : productIds) {
			Boolean availability = productsAvailability.get(productId);
			productUpdated = productUpdated + productRepository.changeAvailibility( productId, availability );
		}
		
		return ResponseEntity.ok(String.format("Product availability changed for %s products", productUpdated));
	}

	public ResponseEntity<Object> deleteProducts(String productIds) {
		
		List<Integer> ids = Arrays.asList(productIds.split(",")).stream().map(Integer::parseInt).toList();
		int deleted = productRepository.softDeleteByIds( ids );
		return ResponseEntity.ok(String.format("%s products deleted successfully.", deleted));
	}

	public ResponseEntity<Object> areProductsValidForCart(List<Integer> productIds) {

		List<Integer> invalidIds = new ArrayList<>();
		for (Integer productId : productIds) {
			boolean valid = productRepository.existsByIdAndDeletedFalse( productId );
			if(!valid) {
				invalidIds.add(productId);
			}
		}
		return invalidIds.isEmpty() ? ResponseEntity.ok(true) 
				: ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
								.body("These ids are not valid : "+invalidIds.toString());
	}

	public ResponseEntity<Object> getProductsByIds(List<Integer> productIds) {
		
		List<Object[]> productsObjs = productRepository.findAllByIdWithEshopName(productIds);
		List<Product> products = new ArrayList<>();
		for (Object[] product : productsObjs) {
			Product newProduct = (Product) product[1];
			newProduct.setEshopName((String) product[0]);
		}
		
		return !products.isEmpty() ? ResponseEntity.ok(products) 
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
								.body("Product not found with given Ids.") ;
	}
	
}
