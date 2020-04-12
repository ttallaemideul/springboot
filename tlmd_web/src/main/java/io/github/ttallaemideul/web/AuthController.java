package io.github.ttallaemideul.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.ttallaemideul.base.BaseController;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

	@GetMapping("/login")
	public String login(Model model) {
		return "web/auth/login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "web/auth/logout";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model model) {
		return "web/auth/access-denied";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		return "web/auth/signup";
	}
}
