package com.yinzifan.security.core.social.qq.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.yinzifan.security.core.social.qq.api.QQ;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ>{

	public QQConnectionFactory(String providerId, String clientId, String clientSecret) {
		super(providerId, new QQServiceProvider(clientId, clientSecret), new QQAdapter());
	}

}
