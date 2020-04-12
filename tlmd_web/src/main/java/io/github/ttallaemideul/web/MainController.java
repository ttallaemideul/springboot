package io.github.ttallaemideul.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.ttallaemideul.base.BaseController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController extends BaseController {

	@GetMapping
	public String main(Model model, Authentication auth) {
		log.debug("auth={}", auth);
		return "web/main/main";
	}
}
