package com.yinzifan.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
	
	ValidateCode createImageCode(HttpServletRequest request);
	
	ValidateCode generate(ServletWebRequest request);
}
