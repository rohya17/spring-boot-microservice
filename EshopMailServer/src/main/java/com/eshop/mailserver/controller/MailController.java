package com.eshop.mailserver.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.models.EmailDetails;
import com.eshop.mailserver.service.MailService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.xml.bind.PropertyException;

@CrossOrigin("*")
@RestController("MailController")
@RequestMapping("/smtp")
public class MailController {
	
	@Autowired
	private MailService mailService;

	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail( @Valid @RequestBody EmailDetails emailDetails) throws MessagingException, PropertyException, IOException{
		return mailService.sendEmail(emailDetails); 
	}
}
