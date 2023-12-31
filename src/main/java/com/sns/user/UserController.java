package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
// http://localhost:7070/user/sign-up-view
	@GetMapping("/sign-up-view")
	public String signUpView(Model model) {
		model.addAttribute("view", "user/signUp");
		return "template/layout";
	}
	// http://localhost:7070/user/sign-in-view
	@GetMapping("/sign-in-view")
	public String singInView(Model model) {
		model.addAttribute("view", "user/signIn");
		return "template/layout";
	}
	
}