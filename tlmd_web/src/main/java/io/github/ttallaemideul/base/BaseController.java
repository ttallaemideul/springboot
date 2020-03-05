package io.github.ttallaemideul.base;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	private static final Logger LOG_BASE = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	protected HttpSession httpSession;
	
}
