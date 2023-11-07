package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	/**
	 * 댓글 쓰기 API
	 * @param session
	 * @param postId
	 * @param commentContent
	 * @return
	 */
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
		result.put("code", 200);
			
		
		return result;
		
		
	}
	
	@DeleteMapping("/delete") 
	public Map<String, Object> delete(
			@RequestParam("commentId") int commentId
			, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		// 로그인 여부 확인
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500);
			result.put("errorMessage", "로그인이 되지 않은 사용자 입니다.");
			return result;
		} 
		// 삭제
		commentBO.deleteCommentById(commentId);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
}
