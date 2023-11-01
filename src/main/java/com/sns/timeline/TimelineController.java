package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.BO.PostBO;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	private PostBO postBO;
	// http://localhost:7070/timeline/timeline-view
	@GetMapping("/timeline-view")
	public String timeLine(Model model) {
		
		model.addAttribute("viewName", "/timeline/timeline");
		return "template/layout";
	}
}
