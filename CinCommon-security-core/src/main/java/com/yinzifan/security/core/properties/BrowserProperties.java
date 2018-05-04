package com.yinzifan.security.core.properties;

public class BrowserProperties {

	private String loginPage = "/default-login.html";

	private LoginType loginType = LoginType.JSON;
	
	private int rememberMeSec = 3600;

	private int rememberMeSeconds = 3600;
	
	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSec() {
		return rememberMeSec;
	}

	public void setRememberMeSec(int rememberMeSec) {
		this.rememberMeSec = rememberMeSec;
	}
	
}
