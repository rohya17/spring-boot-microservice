package com.common.interfaces;

import org.springframework.http.ResponseEntity;

public interface UserInterface {

	ResponseEntity<Object> createOrUpdateUser(Object user);
	ResponseEntity<String> deleteUser(Integer userId);
	ResponseEntity<String> updatePassword( String email, String oldPassword, String newPassword );
}
