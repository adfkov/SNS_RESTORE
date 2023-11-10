package com.sns.post.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.Entity.PostEntity;
import com.sns.post.mapper.PostMapper;
import com.sns.post.mapper.PostRepository;

@Service
public class PostBO {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private FileManagerService fileManager;
	
	
	public List<PostEntity> getPostList() {
			return postRepository.findAllByOrderByIdDesc();	
	}
	
	public void addPost(int userId,String userLoginId,String subject ,String content, MultipartFile file) {
		String imagePath = null;
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
			
			}
		
		
		postMapper.insertPost(userId ,subject ,content, imagePath);
	}
	

	// 글 삭제
	public void deletePostById(int postId) {
		// 기존 글 => 이미지 삭제

		// db 글 삭제

		// db 댓글 삭제

		// db 좋아요
	}

		
}


