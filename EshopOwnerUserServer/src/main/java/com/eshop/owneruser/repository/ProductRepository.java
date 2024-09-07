package com.eshop.owneruser.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.common.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p where lower(p.searchTags) like lower(:searchTags) AND p.eshopId=:shopId AND p.deleted=false ")
	Page<Product> findAllWithFilters(@Param("searchTags") String searchTags, @Param("shopId")Integer shopId, Pageable pageRequest);

	Page<Product> findBySearchTagsContainingAndEshopId(String searchTags, Integer shopId, Pageable pageRequest);

	@Modifying
	@Query("UPDATE Product p SET p.outOfStock=:availability WHERE p.id=:productId ")
	int changeAvailibility( @Param("productId")Integer productId, @Param("availability")Boolean availability);

	@Modifying
	@Query("UPDATE Product p SET p.deleted=true WHERE p.id in (:ids) ")
	int softDeleteByIds(@Param("ids") List<Integer> ids);

	Page<Product> findByDeletedFalse(Pageable pageRequest);

	boolean existsByIdAndDeletedFalse(Integer productId);

	@Query("SELECT e.eshopName,p FROM Product p, Eshop e WHERE p.eshopId=e.id AND p.id in (:productIds)")
	List<Object[]> findAllByIdWithEshopName(@Param("productIds") List<Integer> productIds);

}
