package com.eshop.owneruser.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.owneruser.dto.EshopRegistrationPayload;
import com.eshop.owneruser.service.EshopOwnerService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/eshopOwner")
public class EshopOwnerController {

	@Autowired private EshopOwnerService eshopOwnerService;

	@PostMapping("/createShopAndOwner")
	public ResponseEntity<Object> createEshopAndEshopOwner( @Valid EshopRegistrationPayload eshopRegistrationPayload ) throws IOException{
		return eshopOwnerService.createEshopAndEshopOwner(eshopRegistrationPayload);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEshopAndEshopOwner( @RequestParam("userId") Integer userId){
		return eshopOwnerService.deleteUser(userId);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllEshopOwners(){
		return eshopOwnerService.getAllEshopOwners();
	}
}

