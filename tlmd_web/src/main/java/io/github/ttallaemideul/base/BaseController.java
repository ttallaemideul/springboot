package io.github.ttallaemideul.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {
	private static final Logger LOG_BASE = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	protected HttpSession httpSession;
	
	@Autowired
	protected HttpServletRequest httpRequest;
	
	public ResultData getResultData() {
		ResultData result = new ResultData();
		result.setPath(httpRequest.getRequestURI());
		return result;
	}

	/**
	 * 호출된 컨트롤러의 디버그 모드를 리턴한다.
	 * @return
	 */
	@ModelAttribute("DEBUG_MODE")
	public boolean getDebugMode() {
		boolean debugMode = LoggerFactory.getLogger(this.getClass()).isDebugEnabled();
		if (LOG_BASE.isDebugEnabled()) {
			LOG_BASE.debug("{} : DEBUG_MODE={}", this.getClass(), debugMode);
			LOG_BASE.debug("LOG_BASE={}, getName={}", LOG_BASE, LOG_BASE.getName() );
		}
		return debugMode;
	}
}
