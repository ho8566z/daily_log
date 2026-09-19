package com.office.library.book.admin.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//@Component
@Service
public class UploadFileService {

	public String upload(MultipartFile file) {
		
		boolean result = false;
		
		// File 저장
		String fileOriName = file.getOriginalFilename(); // abc.jpg
		String fileExtension = 
				fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length()); // jpg
		String uploadDir = "C:\\library\\upload\\";
		
		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-", "");  // 123e4567e89b12d3a456426614174000
		
		File saveFile = new File(uploadDir + uniqueName + fileExtension);
		//    C:\\library\\upload\\123e4567e89b12d3a456426614174000.jpg
		
		if (!saveFile.exists())
			saveFile.mkdirs();
		
		try {
			file.transferTo(saveFile);
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if (result) {
			System.out.println("FILE UPLOAD SUCCESS!!");
			return uniqueName + fileExtension;
			
		} else {
			System.out.println("FILE UPLOAD FAIL!!");
			return null;
			
		}
		
	}
	
}
