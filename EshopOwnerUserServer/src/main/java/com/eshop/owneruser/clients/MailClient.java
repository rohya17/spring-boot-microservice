package com.eshop.owneruser.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.common.models.EmailDetails;

@FeignClient(name="ESHOP-MAIL-SERVER",fallback = MailClientFallback.class
// url="${mail.server.url}"
)
public interface MailClient {

	@PostMapping("smtp/sendEmail")
	public ResponseEntity<String> sendEmail(EmailDetails emailDetails);
		
}
