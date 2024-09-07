package com.eshop.owneruser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.owneruser.models.Eshop;

@Repository
public interface EshopRepository extends JpaRepository<Eshop, Integer> {

}
