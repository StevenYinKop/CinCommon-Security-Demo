package com.yinzifan.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yinzifan.exception.UserNotExistException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handlerUserNotExistException(UserNotExistException ex) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", ex.getId());
		map.put("message", ex.getMessage());
		return map;
	}
}
