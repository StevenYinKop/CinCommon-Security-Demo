package com.yinzifan.web.controller;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

	private final static Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);
	
	@GetMapping("/order")
	public String order() throws Exception {
		LOGGER.info("主线程开始");
		Thread.sleep(1000); // 模拟处理业务逻辑过程
		LOGGER.info("主线程结束");
		return "success!!";
	}
	@GetMapping("/asyncOrder")
	public Callable<String> asyncOrder() throws Exception {
		LOGGER.info("主线程开始");
		Callable<String> result = () -> {
			LOGGER.info("副线程开始");
			Thread.sleep(1000); // 模拟处理业务逻辑过程
			LOGGER.info("副线程结束");
			return "success!!";
		};
		Thread.sleep(1000); // 模拟处理业务逻辑过程
		LOGGER.info("主线程结束");
		return result;
	}
}
