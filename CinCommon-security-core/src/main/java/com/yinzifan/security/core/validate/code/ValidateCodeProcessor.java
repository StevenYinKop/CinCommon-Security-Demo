package com.yinzifan.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {
	
	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX= "SESSION_KEY_FOR_CODE_";
	
	/**
	 * 创建验证码
	 * @param request ServletWebRequest用来封装请求和相应, 其中包含了request和response, 并不是只有request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

}
