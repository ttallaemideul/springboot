package io.github.ttallaemideul.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.ttallaemideul.base.BaseController;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@GetMapping
	public String main(Model model) {
		return "web/admin/main";
	}
}
