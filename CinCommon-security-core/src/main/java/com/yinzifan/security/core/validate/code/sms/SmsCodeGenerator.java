package com.yinzifan.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.yinzifan.security.core.properties.SecurityProperties;
import com.yinzifan.security.core.validate.code.ValidateCode;
import com.yinzifan.security.core.validate.code.ValidateCodeGenerator;

@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator{
	
	private SecurityProperties securityProperties;
	
	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
	
	

}
