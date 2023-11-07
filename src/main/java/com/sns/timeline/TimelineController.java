package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.BO.TimelineBO;
import com.sns.timeline.domain.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
		@Autowired
		private TimelineBO timelineBO;
		//	private PostBO postBO;
	// http://localhost:7070/timeline/timeline-view
	@GetMapping("/timeline-view")
	public String timeLine(Model model , HttpSession session) {
//		List<PostEntity> postList = postBO.getPostList();
		
		List<CardView> cardViewList = timelineBO.generateCardViewList();  // view -> controller> TimelineBO <-> PostBO(상호참조 에러) -> BO -> Repository // 화면에 가까운 BO 
		
		model.addAttribute("cardViewList", cardViewList);
		model.addAttribute("viewName", "/timeline/timeline");
//		model.addAttribute("postList", postList);
		
		return "template/layout";
	}
}
