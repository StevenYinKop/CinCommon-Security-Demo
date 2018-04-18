package com.yinzifan.exception;

public class UserNotExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2151938245328703556L;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserNotExistException(String id) {
		super("user not exist");
		this.id = id;
	}
	
	
}
