package com.eshop.enduser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.common.interfaces.UserInterface;
import com.common.utility.Validators;
import com.eshop.enduser.models.EndUser;
import com.eshop.enduser.repository.EndUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EndUserService implements UserInterface{

	@Autowired private BCryptPasswordEncoder passwordEncoder;
	@Autowired private EndUserRepository endUserRepository;

	@Override
	public ResponseEntity<Object>  createOrUpdateUser(Object user) {
		
		EndUser endUserObject = (EndUser) user;
		String message = "User %s successfully.";
		if( !Validators.validIntegerForId(endUserObject.getId()) && !endUserRepository.existsById(endUserObject.getId()) ) {
			endUserObject.setPassword(passwordEncoder.encode(endUserObject.getPassword()));
			String.format(message, "registered");
		}else {
			String.format(message, "updated");
		}
		endUserRepository.save(endUserObject);
		
		return ResponseEntity.ok(message);
	}
	
	@Override
	public ResponseEntity<String> deleteUser(Integer userId) {
		
		if( endUserRepository.existsById(userId) ) {
			endUserRepository.deleteById(userId);
			return ResponseEntity.ok("User deleted successfully.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User not found with id %s.", userId));
	}

	@Override
	public ResponseEntity<String> updatePassword(String email, String oldPassword, String newPassword) {
		
		// get user
		Optional<EndUser> userOptional = endUserRepository.findByEmail(email);
		if(!userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("User with email %s not found!", email));
		}
		EndUser endUser = userOptional.get();
		
		// if old password matches save password 
		if(passwordEncoder.matches(oldPassword, endUser.getPassword())) {
			endUser.setPassword(passwordEncoder.encode(newPassword));
			endUserRepository.save(endUser);
			return ResponseEntity.ok("Password updated successfully.");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password does not match.");
	}

	public ResponseEntity<Object> getAllEndUser() {
		List<EndUser> endUsers = endUserRepository.findAll();
		return ResponseEntity.ok(endUsers);
	}
	
}
