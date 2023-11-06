package com.sns.timeline.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.BO.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.post.BO.PostBO;
import com.sns.post.Entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.BO.userBO;
import com.sns.user.Entity.UserEntity;

@Service
public class TimelineBO {
	@Autowired
	private PostBO postBO;
	@Autowired
	private userBO userBO;
	@Autowired
	private CommentBO commentBO;
	// input : x , output: List<CardView>
	public List<CardView> generateCardViewList() {
		List<CardView> cardViewList = new ArrayList<>();
		// post로 내리고 있던 것을 cardView 로 변환한다.
		
		// 글 목록을 가져온다. List<PostEntity>를 
		List<PostEntity> postList = postBO.getPostList();
		//
		
		// 반복문으로 순회
		// postEntity => CardView -> cardViewList에 담는다.
		for(PostEntity post: postList) {
			CardView cardView = new CardView();
			cardView.setPost(post);
			
			UserEntity user = userBO.findById(post.getUserId());
			cardView.setUser(user);
			
			// 댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			cardView.setCommentList(commentList);
			
			// 좋아요 카운트
			
			// 내가 좋아요 눌렀는지 여부
			
			
			
			// ★★★★★★ 마지막에 CardViewList에 card를 넣는다.
			cardViewList.add(cardView);
			
			
		}
		
			return cardViewList;
		
//		for(int i = 0; i < postList.size(); i++) {
//			CardView cardView = new CardView();
//			// post set
//			cardView.setPost(postList.get(i));
//			cardViewList.add(cardView);
//			// user set
//			UserEntity user = userBO.findById(postList.get(i).getUserId());
//			cardView.setUser(user);
//		}
//		
	}
}
