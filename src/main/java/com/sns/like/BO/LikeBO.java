package com.sns.like.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {
	@Autowired
	private LikeMapper likeMapper;
	
	public void tellLikeToggle(int postId, int userId) {
		  
		if(likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) == 0) {
			likeMapper.insertLikeToggle(postId, userId);
		} else {
			likeMapper.deleteLikeToggle(postId, userId);
		}
		 
	}
	
	// INPUT: 글번호 / OUTPUT: 개수(COUNT)
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	// input: 글번호 ,userId(Integer)		output: 채워진지 여부(boolean)
	public boolean filledLike(int postId, Integer userId) {
		// 비로그인
		if(userId == null) {			
			return false;
		} 
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, postId) > 0;
		
	}
	

}
