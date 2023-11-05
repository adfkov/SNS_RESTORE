package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\CHUNG\\Desktop\\정재우\\Spring\\SNS\\workspace\\images/";
		
	
	public String saveFile(String loginId, MultipartFile file) {
	// 서버에 폴더 생성
	String directoryName = loginId + "_" + System.currentTimeMillis();
	String filePath = FILE_UPLOAD_PATH + directoryName;
//C:\\Users\\CHUNG\\Desktop\\정재우\\Spring\\SNS\\workspace\\images/aaaa_현재시간
	File directory = new File(filePath);
	if(directory.mkdir() == false) {
		return null;
	}
	
	try {
		byte[] bytes;
		bytes = file.getBytes();

		Path path = Paths.get(filePath + "/" + file.getOriginalFilename());
		Files.write(path, bytes);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} // byte 단위로 업로드
	// 파일 업로드 성공 시 웹 이미지 url path를 리턴
			// 주소는 이렇게 될 것이다. (예언?) 
				// http://localhost/images/aaaa_현재시간/sun.png
			// /images/aaaa_178945646/sun.png
			return "/images/" + directoryName + "/" + file.getOriginalFilename();
//			
	}
	
}
