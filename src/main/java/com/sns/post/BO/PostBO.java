package com.sns.post.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.Entity.PostEntity;
import com.sns.post.mapper.PostRepository;

@Service
public class PostBO {
	@Autowired
	private PostRepository postRepository;
	
	public List<PostEntity> getPostList() {
			return postRepository.findAllByOrderByIdDesc();	
		
	}
}
