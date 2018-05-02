package com.yinzifan.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.yinzifan.security.core.properties.SecurityProperties;

@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator{
	
	private SecurityProperties securityProperties;
	
	@Override
	public ValidateCode createImageCode(HttpServletRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	@Override
	public ValidateCode generate(ServletWebRequest request) {
		return null;
	}
	
	

}
