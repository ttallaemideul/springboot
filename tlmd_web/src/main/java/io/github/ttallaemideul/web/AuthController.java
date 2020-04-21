package io.github.ttallaemideul.web;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.ttallaemideul.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import tlmd.Util;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController extends BaseController {

	@GetMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error) {
		if("true".equals(error)) {
			// 로그인 오류시 값이 세팅됨.
			Object authEx = httpRequest.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (Util.isEmpty(authEx)) {
				authEx = httpSession.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			}
			if (log.isDebugEnabled()) {
				log.debug("error={}", error);
				log.debug("auth", authEx);
			}
			if(authEx instanceof BadCredentialsException) {
				BadCredentialsException be = (BadCredentialsException) authEx;
				if (log.isDebugEnabled()) {
					log.debug("be={}", be.getMessage());
				}
				model.addAttribute("error", be.getMessage());
			}
		}
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
