package com.sns.post.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.Entity.PostEntity;
import com.sns.post.mapper.PostMapper;
import com.sns.post.mapper.PostRepository;

@Service
public class PostBO {
	@Autowired
	private PostRepository postRepository;
	private PostMapper postMapper;
	
	
	public List<PostEntity> getPostList() {
			return postRepository.findAllByOrderByIdDesc();	
	}
	
	public void addPost(int userId,String content, MultipartFile file) {
		postMapper.insertPost(userId, content, file);
	}
}
