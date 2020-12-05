package com.yinzifan.security.core.validate.code.sms;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.yinzifan.security.core.properties.SecurityConstants;
import com.yinzifan.security.core.validate.code.ValidateCode;
import com.yinzifan.security.core.validate.code.impl.AbstractValidateCodeProcessor;

public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{

	@Autowired
	private SmsCodeSender smsCodeSender;
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile = ServletRequestUtils.getRequiredStringParameter((ServletRequest)request.getRequest(), paramName);
		smsCodeSender.send(mobile, validateCode.getCode());
	}

}
