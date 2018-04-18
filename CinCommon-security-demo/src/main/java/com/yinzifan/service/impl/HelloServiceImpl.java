package com.yinzifan.service.impl;

import org.springframework.stereotype.Service;

import com.yinzifan.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public String greeting(String msg) {
		return "hello! " + msg;
	}

}
