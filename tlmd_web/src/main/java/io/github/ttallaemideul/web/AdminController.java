package io.github.ttallaemideul.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.ttallaemideul.base.BaseController;
import io.github.ttallaemideul.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping
	public String main(Model model) {
		model.addAttribute("userList", adminService.getUserList());
		return "web/admin/admin_main";
	}
}
