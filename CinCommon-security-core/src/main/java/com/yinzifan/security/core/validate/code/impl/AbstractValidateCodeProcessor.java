package com.yinzifan.security.core.validate.code.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import com.yinzifan.security.core.validate.code.ValidateCode;
import com.yinzifan.security.core.validate.code.ValidateCodeGenerator;
import com.yinzifan.security.core.validate.code.ValidateCodeProcessor;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor{
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	/**
	 * 收集容器中所有的{@link ValidateCodeGenerator}的实现
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;
	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}
	
	private C generate(ServletWebRequest request) {
		String type = getProcessorType(request);
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
		return (C)validateCodeGenerator.generate(request);
	}
	
	private void save(ServletWebRequest request, C validateCode) {
		sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
	}
	
	/**
	 * 发送校验码，由子类实现
	 * 
	 * @param request
	 * @param validateCode
	 * @throws Exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

	
	private String getProcessorType(ServletWebRequest request) {
		return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
	}
	
}
