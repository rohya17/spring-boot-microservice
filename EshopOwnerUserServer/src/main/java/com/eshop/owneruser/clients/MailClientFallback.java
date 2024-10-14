package com.eshop.owneruser.clients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.common.models.EmailDetails;

public class MailClientFallback implements MailClient {

	@Override
	public ResponseEntity<String> sendEmail(EmailDetails emailDetails) {
		return ResponseEntity
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("Mail server not responding please try after sometime.");
	}

}
