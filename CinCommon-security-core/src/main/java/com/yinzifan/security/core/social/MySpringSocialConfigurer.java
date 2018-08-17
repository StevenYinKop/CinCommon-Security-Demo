package com.yinzifan.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class MySpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessUrl;
	
	public MySpringSocialConfigurer(String filterProcessUrl) {
		super();
		this.filterProcessUrl = filterProcessUrl;
	}

	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessUrl);
		return (T) filter;
	}

}
