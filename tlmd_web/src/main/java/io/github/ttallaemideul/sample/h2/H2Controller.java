package io.github.ttallaemideul.sample.h2;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.ttallaemideul.base.BaseController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sample/h2")
public class H2Controller extends BaseController {
	
	@Autowired
	private H2Service h2Service;
	
	@GetMapping
	public String main(Model model) {
		List<Map<String, Object>> users = h2Service.getUsers();
		if(log.isDebugEnabled()) {
			log.debug("users={}", users);
		}
		model.addAttribute("serverNow", h2Service.getServerNow());
		model.addAttribute("users", users);
		return "sample/h2/main";
	}
	
}
