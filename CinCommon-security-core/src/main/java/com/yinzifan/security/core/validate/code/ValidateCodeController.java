package com.yinzifan.security.core.validate.code;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.yinzifan.security.core.properties.SecurityConstants;

@RestController
public class ValidateCodeController {
	
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;
	
//	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
//	
//	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy(); 
//	
//	@Autowired
//	private ValidateCodeGenerator imageCodeGenerator;
//	
//	@Autowired
//	private ValidateCodeGenerator smsCodeGenerator;
//	
//	@Autowired
//	private SmsCodeSender smsCodeSender;
	
//	@GetMapping("/code/image")
//	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		ImageCode imageCode = (ImageCode) imageCodeGenerator.createImageCode(request);
//		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
//		// 将图片写到
//		
//		
//	}
//	@GetMapping("/code/sms")
//	public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
//		ValidateCode smsCode = smsCodeGenerator.createImageCode(request);
//		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
//		String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
//		smsCodeSender.send(mobile, smsCode.getCode());
//	}
	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 * 
	 * @param request
	 * @param response
	 * @param type
	 * @throws Exception
	 */
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
		validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
	}
	

}
