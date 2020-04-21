package io.github.ttallaemideul.sample.thymeleaf;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.ttallaemideul.base.BaseController;
import io.github.ttallaemideul.utils.UtilServlet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sample/thymeleaf")
public class ThymeleafController extends BaseController {

	@GetMapping
	public String main(Model model) {
		model.addAttribute("serverTime", "<b>" + new Date() + "</b>");
		model.addAttribute("key_x", "from model");
		if (httpSession != null) {
			httpSession.setAttribute("key_x", "from session");
			httpSession.setAttribute("name", "딸내미들");
			httpSession.setAttribute("영어이름", "TtalLaeMideul");
		}
		model.addAttribute("httpSession", UtilServlet.getSessionInfo(httpSession));
		if (log.isDebugEnabled()) {
			log.debug("model={}", model);
			printRequestParams();
		}
		return "sample/thymeleaf/main";
	}

	@GetMapping("/basic_object")
	public String basicObject(Model model) {
		return "sample/thymeleaf/basic_object";
	}

	@GetMapping("/javascript")
	public String javascript(Model model) {
		return "sample/thymeleaf/javascript";
	}
}
