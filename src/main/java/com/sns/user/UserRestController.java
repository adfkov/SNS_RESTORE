package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		UserEntity user = userBO.findByLoginId(loginId);
		if(user == null) {
			// 로그인 진행
			result.put("code",200);
			result.put("isDuplicated", false);
		} else {
			result.put("isDuplicated", true);
		}
		
		// json 리턴
		
		
		return result;
	}
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		// password md5
		String hashedPassword = EncryptUtils.md5(password);
		
		
		//db 조회
		UserEntity user = userBO.findByLoginIdAndPassword(loginId, hashedPassword) ; // NULL 아니면 UserEntity
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		
		if(user != null) {
			// 로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("userId" ,  user.getId()); // key , value / 모든 페이지에서 사용 가능한 세션 주머니
			session.setAttribute("userName", user.getName());
			session.setAttribute("userLoginId", user.getLoginId());
			
			result.put("code",200);
			result.put("result", "성공");
		} else {
			// 로그인 불가
			result.put("code", 500);
			result.put("errorMessage", "존재하지 않는 사용자입니다.");
		}
		return result;
		
	}
	
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email
			) {
		// 패스워드 원본을 저장하면 안되고 패스워드를 해싱 -> 가장 보안이 취약한 방식 - md5 알고리즘
		// aaaa => 74b8733745420d4d33f80c4663dc5e5
		 String hashedPassword = EncryptUtils.md5(password);
		 
		// DB insert
		Integer id = userBO.addUser(loginId, hashedPassword, name, email);
		// 응답값(성공 여부)
		Map<String, Object> result = new HashMap<>();
		if(id == null) {
			result.put("code", 500);
			result.put("errorMessage", "회원가입 하는데 실패했습니다.");
		} else {
		
			result.put("code", 200);
			result.put("result", "성공");
		}
		return result;
	}
	// 로그인
	
	
}
