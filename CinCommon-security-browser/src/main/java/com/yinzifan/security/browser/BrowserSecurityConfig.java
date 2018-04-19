package com.yinzifan.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin() // 用表单登录进行身份认证
			.and() // 
			.authorizeRequests()
			.anyRequest() // 任何请求
			.authenticated(); // 都要进行身份认证
		
	}
}
