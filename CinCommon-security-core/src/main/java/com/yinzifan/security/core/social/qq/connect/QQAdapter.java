package com.yinzifan.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.yinzifan.security.core.social.qq.api.QQ;
import com.yinzifan.security.core.social.qq.api.QQUserInfo;

public class QQAdapter implements ApiAdapter<QQ> {  // 当前适配器所适配的Api类型

	/**
	 * 测试api提供商是否可用
	 */
	@Override
	public boolean test(QQ api) {
		return false;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		// 设置个人主页, QQ不存在个人主页, 跳过
//		values.setProfileUrl(profileUrl);
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		// do nothing
	}

}
