package io.github.ttallaemideul.base;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import io.github.ttallaemideul.utils.UtilDate;

@Component
public class BaseErrorAttributes extends DefaultErrorAttributes {
	
	public BaseErrorAttributes() {
		super(false);
	}
	
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
		errorAttributes.put("timestamp", UtilDate.getNowFullSSS());
		return errorAttributes;
	}
}
