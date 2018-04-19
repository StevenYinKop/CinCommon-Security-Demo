package com.yinzifan.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {
	private final static Logger LOGGER = LoggerFactory.getLogger(MockQueue.class);
	private String placeOrder;
	private String completeOrder;
	public String getPlaceOrder() {
		return placeOrder;
	}
	public void setPlaceOrder(String placeOrder) {
		new Thread(()->  {
			LOGGER.info("收到订单: " + placeOrder);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			this.completeOrder = placeOrder;
			LOGGER.info("订单处理完成: " + placeOrder);
		}).start();
	}
	public String getCompleteOrder() {
		return completeOrder;
	}
	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
}
