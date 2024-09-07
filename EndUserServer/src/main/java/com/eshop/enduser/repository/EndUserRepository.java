package com.eshop.enduser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eshop.enduser.models.EndUser;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, Integer> {

	@Query(nativeQuery = true, value = "Select eu.* from enduser eu where lower(eu.email) like (:email)")
	Optional<EndUser> findByEmail(@Param("email") String email);

}
