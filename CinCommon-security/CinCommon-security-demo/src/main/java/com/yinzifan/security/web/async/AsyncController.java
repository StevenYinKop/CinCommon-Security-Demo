package com.yinzifan.security.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {

	private final static Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);
	
	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferredResultHolder deferredResultHolder;
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
	
	@GetMapping("/deferredResult")
	public DeferredResult<String> deferredResult() throws Exception {
		LOGGER.info("主线程开始");
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);
		LOGGER.info("主线程结束");
		return result;
	} 
}
