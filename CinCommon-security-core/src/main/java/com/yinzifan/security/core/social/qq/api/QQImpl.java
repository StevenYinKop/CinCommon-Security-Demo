package com.yinzifan.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String URL_GET_OPENID= "https://graph.qq.com/oauth2.0/me?access_token=%s";
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	private String appId;  // 系统的配置信息
	private String openId;  // 通过access_token获得, 不需要从外部取得
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public QQImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.OAUTH_TOKEN_PARAMETER); // 使用父类的restTemplate发送请求时, 会自动将ACCESS_TOKEN带上
		this.appId = appId;
		String result = getRestTemplate().getForObject(String.format(URL_GET_OPENID, accessToken), String.class);
		logger.debug("QQImpl.QQImpl(): => result: {}", result);

		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}



	@Override
	public QQUserInfo getUserInfo() {
		String url = String.format(URL_GET_USERINFO, appId, openId);
		
		String result = getRestTemplate().getForObject(url, String.class);
		logger.debug("QQImpl.getUserInfo(): => result: {}", result);
		QQUserInfo userInfo = null;
		try {
			userInfo = objectMapper.readValue(result, QQUserInfo.class); 
			userInfo.setOpenId(openId);
			return userInfo;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
