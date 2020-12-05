package com.yinzifan.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yinzifan.security.core.properties.SecurityProperties;
import com.yinzifan.security.core.validate.code.image.ImageCodeGenerator;
import com.yinzifan.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.yinzifan.security.core.validate.code.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	// 以增量的形式适应变化, 
	@ConditionalOnMissingBean(name = "imageCodeGenerator") 
	// 会现在spring容器中寻找是否已经存在name=imageCodeGenerator, 
	// 如果存在则不用下面的配置了, 直接使用已存在的
	public ValidateCodeGenerator imageCodeGenerator() {
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
	
	@Bean
	// 以增量的形式适应变化, 
	@ConditionalOnMissingBean(SmsCodeSender.class) 
	// 会现在spring容器中寻找是否已经存在name=imageCodeGenerator, 
	// 如果存在则不用下面的配置了, 直接使用已存在的
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}
