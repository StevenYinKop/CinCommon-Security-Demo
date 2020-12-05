package com.yinzifan.security.service.impl;

import org.springframework.stereotype.Service;

import com.yinzifan.security.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public String greeting(String msg) {
		return "hello! " + msg;
	}

}
