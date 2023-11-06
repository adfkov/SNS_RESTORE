package com.sns.comment.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.BO.userBO;
import com.sns.user.Entity.UserEntity;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private userBO userBO; // BO 는 BO만 부른다. ( 자기 것이 아니라면)
	
	public void addComment(int userId, int postId, String commentContent) {
		commentMapper.insertComment(userId, postId, commentContent);
	}
	
	// input: 	, output:List<CommentView>
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 글에 해당하는 댓글 목록 가져오기 List<Comment>
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId); // 글 번호와 일치하는 댓글들
		
		// 반복문 순회
		for(Comment comment: commentList) {
			CommentView commentView = new CommentView();
			// 댓글 내용 껴넣기
			commentView.setComment(comment);
			//
			 UserEntity user = userBO.findById(comment.getUserId());
			 commentView.setUser(user);
			 
			 // 댓글들
			 commentViewList.add(commentView);
		}
		
		return commentViewList;
		// List<Comment> => List<CommentView>
		
		

	}
}
