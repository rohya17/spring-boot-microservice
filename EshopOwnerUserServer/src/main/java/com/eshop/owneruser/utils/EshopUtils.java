package com.eshop.owneruser.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class EshopUtils {

	public static boolean isValidString( String string ) {
		return string != null && !string.isEmpty();
	}

	public static boolean isValidInteger( Integer integer ) {
		return integer != null && integer.intValue() > 0;
	}

	public static String formatToSearchString( String searchString ) {
		return "%"+searchString+"%";
	}
	
	public static boolean storeImage( MultipartFile image, String path, String newNameForFile ) throws IOException{
		
		if(!path.endsWith(File.separator)) {
			path = path.concat(File.separator);
		}
		// converts a pathString to a path
		Path uploadPath = Paths.get(path);

		// if directory doesnot exists it will create directory
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		// return input stream to read contents from file
		InputStream inputStream = image.getInputStream();

		// resolves the path
		Path filePath = uploadPath.resolve(newNameForFile);

		// copies file contents from inputstream to filepath and if exists same it will replace that
		long bytes = Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

		inputStream.close();
		
		return bytes > 0 ;
	}

	public static String generateFileName(String fileNameFormat, MultipartFile file, Object ...variables ) {
		String orgFileName = file.getOriginalFilename();
		String fileExtention = orgFileName.substring(orgFileName.lastIndexOf("."));
		String fileNameWoExtention = orgFileName.substring(0,orgFileName.lastIndexOf("."));
		return String.format(fileNameFormat, fileNameWoExtention, variables).concat(fileExtention);
	}

	public static boolean isValidFile(MultipartFile productImage) {
		return productImage != null && productImage.getOriginalFilename() != null;
	}
}
