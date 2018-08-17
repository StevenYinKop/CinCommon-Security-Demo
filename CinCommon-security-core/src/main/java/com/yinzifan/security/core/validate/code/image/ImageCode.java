package com.yinzifan.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.yinzifan.security.core.validate.code.ValidateCode;

public class ImageCode extends ValidateCode{

	// 验证码图片
	private BufferedImage image;
	
	
	public ImageCode(BufferedImage image, String code, int expireSec) {
		super(code, expireSec);
		this.image = image;
	}

	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public ImageCode() {
		super();
	}
	
}
