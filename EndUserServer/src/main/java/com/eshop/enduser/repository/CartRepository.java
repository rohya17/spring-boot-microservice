package com.eshop.enduser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eshop.enduser.models.CartItems;

@Repository
public interface CartRepository extends JpaRepository<CartItems, Integer> {

	@Modifying
	@Query("DELETE FROM CartItems ci WHERE ci.endUserId=:endUserId")
	int deleteAllByEndUserId(@Param("endUserId")Integer endUserId);

	List<CartItems> findByEndUserId(Integer endUserId);

}
