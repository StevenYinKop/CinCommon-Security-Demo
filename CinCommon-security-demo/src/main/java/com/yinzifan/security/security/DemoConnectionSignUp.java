package com.yinzifan.security.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

public class DemoConnectionSignUp implements ConnectionSignUp{
	@Override
	public String execute(Connection<?> connection) {
		// 根据社交用户信息默认创建用户并返回用户的唯一标示
		//		获得第三方用户的基本信息;
		//		connection.getKey().getProviderId();
		//		connection.getKey().getProviderUserId();
		//		connection.getDisplayName();
		//		connection.getImageUrl();
		//		connection.getProfileUrl();
		//		connection.getApi();
		return connection.getDisplayName();
	}

}
