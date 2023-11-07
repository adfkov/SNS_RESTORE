package com.sns.like.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {
	public int likeToggle(
			@Param("postId") int postId
			,@Param("userId") int userId
			);
	
	public void deleteLikeToggle(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void insertLikeToggle(
			@Param("postId") int postId,
			@Param("userId") int userId);
}
