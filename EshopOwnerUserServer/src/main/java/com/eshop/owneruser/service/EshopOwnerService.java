package com.eshop.owneruser.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.common.interfaces.UserInterface;
import com.common.models.EmailDetails;
import com.eshop.owneruser.clients.MailClient;
import com.eshop.owneruser.dto.EshopRegistrationPayload;
import com.eshop.owneruser.models.Eshop;
import com.eshop.owneruser.models.EshopOwner;
import com.eshop.owneruser.repository.EshopOwnerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EshopOwnerService implements UserInterface{

	@Autowired private EshopOwnerRepository eshopOwnerRepository;
	@Autowired private EshopService eshopService;
	@Autowired private BCryptPasswordEncoder passwordEncoder;
	@Autowired private MailClient mailClient;
	
	public ResponseEntity<Object> createEshopAndEshopOwner(EshopRegistrationPayload user) throws IOException {
				
		// separate eshop data and create eshop
		Eshop eshop = new Eshop();
		BeanUtils.copyProperties(user, eshop);
		ResponseEntity<Object> createdEshop = eshopService.createOrUpdateEshop(eshop, user.getEshopCoverImage());
		if(createdEshop.getStatusCode() != HttpStatus.OK ) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(createdEshop.getBody());
		}
		eshop = (Eshop) createdEshop.getBody();
		
		// separate Eshop owner data 
		EshopOwner eshopOwner = new EshopOwner();
		BeanUtils.copyProperties(user, eshopOwner);
		eshopOwner.setPassword(passwordEncoder.encode(eshopOwner.getPassword()));
		eshopOwner.setEshop(eshop);
		
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipents(eshopOwner.getEmail());		
		emailDetails.setSubject(String.format("Welcome to E-shop %s owner", eshop.getEshopName()));
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("email-template-name", "welcome-eshop.html");
		params.put("userName", eshopOwner.getGoodName());
		emailDetails.setParams(params);
		
		ResponseEntity<String> response = mailClient.sendEmail( emailDetails );
		if(response.getStatusCode() != HttpStatus.OK) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( response.getBody() );
		}
		
		// save eshop owner
		eshopOwnerRepository.save(eshopOwner);
		
		return ResponseEntity.ok("Eshop registered successfully!!!");
	}

	@Override
	public ResponseEntity<String> deleteUser(Integer userId) {
		if(eshopOwnerRepository.existsById(userId)) {
			eshopOwnerRepository.deleteById(userId);
			return ResponseEntity.ok("User deleted successfully.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id : "+userId);
	}

	@Override
	public ResponseEntity<String> updatePassword(String email, String oldPassword, String newPassword) {
		// get user
		Optional<EshopOwner> eshopOwnerOptional = eshopOwnerRepository.findByEmail(email);
		if(!eshopOwnerOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("User with email %s not found!", email));
		}
		EshopOwner eshopOwner = eshopOwnerOptional.get();
		
		// if old password matches save password 
		if(passwordEncoder.matches(oldPassword, eshopOwner.getPassword())) {
			eshopOwner.setPassword(passwordEncoder.encode(newPassword));
			eshopOwnerRepository.save(eshopOwner);
			return ResponseEntity.ok("Password updated successfully.");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password does not match");
	}

	@Override
	public ResponseEntity<Object> createOrUpdateUser(Object user) {
		
		// Eshop owner data 
		EshopOwner eshopOwner = (EshopOwner) user;
		BeanUtils.copyProperties(user, eshopOwner);
		eshopOwner.setPassword(passwordEncoder.encode(eshopOwner.getPassword()));
		eshopOwner.setEshop(new Eshop());
		
		// save eshop owner
		eshopOwnerRepository.save(eshopOwner);
		
		return ResponseEntity.ok("Eshop owner registered successfully!!!");
	}

	public ResponseEntity<Object> getAllEshopOwners() {
		List<EshopOwner> eshopOwners = eshopOwnerRepository.findAll();
		return ResponseEntity.ok(eshopOwners);
	}

}
