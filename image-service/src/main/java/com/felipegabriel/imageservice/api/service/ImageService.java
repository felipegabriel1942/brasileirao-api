package com.felipegabriel.imageservice.api.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
	
	private static final String FILE_PATH = "C:\\images\\";
	
	public String findImage(String fileName) throws IOException {
		if (imageNotFound(fileName)) {
			return null;
		}
		
		InputStream inputStream = new FileInputStream(FILE_PATH + fileName + ".png");
		
		byte[] imageBytes = IOUtils.toByteArray(inputStream);
		
		inputStream.close();
		
		return Base64.getEncoder().encodeToString(imageBytes);
	}
	
	private boolean imageNotFound(String fileName) {
		Path path = Paths.get(FILE_PATH + fileName + ".png");
		
		return !Files.exists(path);
	}
}
