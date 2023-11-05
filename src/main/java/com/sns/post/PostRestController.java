package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.BO.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create-post")
	public Map<String, Object> createPost(
			@RequestParam("subject") String subject
			,@RequestParam("content") String content
			,@RequestParam(value="file", required = false) MultipartFile file
			,HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String) session.getAttribute("userLoginId");

		//String content, MultipartFile file
		// DB insert
		postBO.addPost(userId, userLoginId, subject ,content, file);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "success");
		return result;
	}
	
}
