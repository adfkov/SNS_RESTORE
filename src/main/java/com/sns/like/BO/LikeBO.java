package com.sns.like.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {
	@Autowired
	private LikeMapper likeMapper;
	
	public void tellLikeToggle(int postId, int userId) {
		  
		if(likeMapper.likeToggle(postId, userId) == 0) {
			likeMapper.insertLikeToggle(postId, userId);
		} else {
			likeMapper.deleteLikeToggle(postId, userId);
		}
		 
	}
	
	

}
