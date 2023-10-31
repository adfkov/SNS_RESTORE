package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.BO.userBO;
import com.sns.user.Entity.UserEntity;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private userBO userBO;
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> test(
			@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();
		// db 조회

		// json 리턴
		result.put("code",200);
		result.put("isDuplicated", true);
		
		
		return result;
	}
	
	@PostMapping("/user/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password) {
		// password md5
		String hashedPassword = EncryptUtils.md5(password);
		
		
		//db 조회
		UserEntity user = userBO.findByLoginIdAndPassword(loginId, hashedPassword) ; // NULL 아니면 UserEntity
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		
		
		return result;
	}
}
