package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.BO.LikeBO;

@RestController
public class LikeRestController {
	// GET:		/like?postId=13 	@RequestParam("postId")
	// GET:		/like/13 			@PathVariable
	@Autowired 
	private LikeBO likeBO;
	
	@RequestMapping("/like/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable int postId
			,HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null) {
			result.put("code", 500);
			result.put("errorMessage", "로그인 안 됐습니다.");
			
			return result;
		}
			
			likeBO.tellLikeToggle(postId, userId);
			
			
			result.put("code", 200);
			return result;
			
		
	
				
		// BO 호출 -> like 여부 체크 , RestController는 일하지 않고 호출한다.
		
		// 응답값
	}
	
}
