package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.BO.CommentBO;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/add-comment")
	public Map<String, Object> addComment(
			HttpSession session
			,@RequestParam("postId") int postId
			,@RequestParam("commentContent") String commentContent) {
		
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer) session.getAttribute("userId");
		// DB insert
		commentBO.addComment(userId, postId, commentContent);
		
		// 응답값
		if(userId == null) {
			result.put("code", 200);
			}
		
		return result;
		
		
	}
}
