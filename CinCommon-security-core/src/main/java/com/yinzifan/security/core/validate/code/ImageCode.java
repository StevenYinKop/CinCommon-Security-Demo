package com.yinzifan.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

	// 验证码图片
	private BufferedImage image;
	// 生成的随机数
	private String code;
	// 过期时间
	private LocalDateTime expireTime;

	
	
	public ImageCode(BufferedImage image, String code, int expireSec) {
		super();
		this.image = image;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireSec);
	}

	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
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

	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super();
		this.image = image;
		this.code = code;
		this.expireTime = expireTime;
	}

	public ImageCode() {
		super();
	}
	
}
