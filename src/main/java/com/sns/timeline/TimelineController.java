package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.BO.PostBO;
import com.sns.post.Entity.PostEntity;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	@Autowired
	private PostBO postBO;
	// http://localhost:7070/timeline/timeline-view
	@GetMapping("/timeline-view")
	public String timeLine(Model model) {
//		List<PostEntity> postList = postBO.getPostList();
		
		List<CardView> cardViewList = ;
		model.addAttribute("cardViewList", cardViewList);
		model.addAttribute("viewName", "/timeline/timeline");
//		model.addAttribute("postList", postList);
		
		return "template/layout";
	}
}
