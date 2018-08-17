package com.yinzifan.security.core.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.yinzifan.security.core.social.qq.api.QQ;
import com.yinzifan.security.core.social.qq.api.QQImpl;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> // 泛型指的是Api接口的类型
{
	private String appId; // 对于QQ来说, appId是固定唯一的, 不会变
	
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	public QQServiceProvider(String clientId, String clientSecret) {
//		super(oauth2Operations);
		// 使用Spring Social提供的默认的
		/**
		 * Constructs an OAuth2Template for a given set of client credentials. 
		 * Assumes that the authorization URL is the same as the authentication URL.
		 * @param clientId the client ID
		 * @param clientSecret the client secret
		 * @param authorizeUrl the base URL to redirect to when doing authorization code or implicit grant authorization //导向的服务器url
		 * @param accessTokenUrl the URL at which an authorization code, refresh token, or user credentials may be exchanged for an access token. //服务器统一授权后, 返回了授权码, 将授权码传送到的url
		 */
		super(new QQOAuth2Template(clientId, clientSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = clientId;
	}

	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

	
}
