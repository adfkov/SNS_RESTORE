package com.sns.comment.domain;

import com.sns.user.Entity.UserEntity;

import lombok.Data;

@Data
public class CommentView {
	// 댓글 내용, 댓글 쓴 이
//	@Autowired
//	private CommentBO commentBO;
//	@
	private Comment comment;
	private UserEntity user;
//	
//	@Column(name="userId")
//	private int userId;
//	private String content;
	
	
	
}
