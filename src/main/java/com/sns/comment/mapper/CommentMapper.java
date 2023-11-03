package com.sns.comment.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
	public void insertComment
	(@Param("userId") int userId
	,@Param("postId") int postId
	,@Param("commentContent") String commentContent);
}
