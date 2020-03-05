package io.github.ttallaemideul.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.ttallaemideul.base.BaseController;

@Controller
@RequestMapping("/")
public class MainController extends BaseController {

	@GetMapping
	public String main(Model model) {
		return "web/main/main";
	}
}
