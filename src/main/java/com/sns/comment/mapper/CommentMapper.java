package com.sns.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.domain.Comment;

@Repository
public interface CommentMapper {
	public void insertComment
	(@Param("userId") int userId
	,@Param("postId") int postId
	,@Param("commentContent") String commentContent);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public void deleteCommentById(int id);
}
