package com.sns.comment.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.mapper.CommentMapper;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	public void addComment(int userId, int postId, String commentContent) {
		commentMapper.insertComment(userId, postId, commentContent);
	}
}
