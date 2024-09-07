package com.eshop.owneruser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.owneruser.models.EshopOwner;

@Repository
public interface EshopOwnerRepository extends JpaRepository<EshopOwner, Integer> {

	Optional<EshopOwner> findByEmail(String email);

}
