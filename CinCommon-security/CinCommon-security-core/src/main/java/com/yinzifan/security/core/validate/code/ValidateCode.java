package com.yinzifan.security.core.validate.code;

import java.time.LocalDateTime;

public class ValidateCode {

	// 生成的随机数
	private String code;
	// 过期时间
	private LocalDateTime expireTime;
	
	public ValidateCode() {
		super();
	}
	public ValidateCode(String code, LocalDateTime expireTime) {
		super();
		this.code = code;
		this.expireTime = expireTime;
	}
	public ValidateCode(String code, int expireIn) {
		super();
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LocalDateTime getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
	
}
