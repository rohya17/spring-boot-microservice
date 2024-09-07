package com.eshop.owneruser.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.owneruser.models.Eshop;
import com.eshop.owneruser.repository.EshopRepository;
import com.eshop.owneruser.utils.EshopUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EshopService {

	@Autowired private EshopRepository eshopRepository;
	private static final String FILE_NAME_FORMAT = "%s - %s";
	
	public ResponseEntity<Object> createOrUpdateEshop( Eshop eshop, MultipartFile coverImage) throws IOException{
		
		// if cover image is not null save image
		if( !EshopUtils.isValidInteger(eshop.getId()) && !EshopUtils.isValidFile(coverImage) ) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Cover Image is mandatory to create e-shop.");
		}
		
		if( EshopUtils.isValidFile(coverImage) ) {
			String storagePath = "D:/";
			String generatedFileName = EshopUtils.generateFileName(FILE_NAME_FORMAT, coverImage, "");
			boolean saved = EshopUtils.storeImage(coverImage, storagePath, generatedFileName );
			if(saved) {
				eshop.setEshopCoverImage(generatedFileName);
			}
		}
		
		// save the eshop object
	 	Eshop savedObject = eshopRepository.save(eshop);
		return ResponseEntity.ok(savedObject);
		
	}
}
