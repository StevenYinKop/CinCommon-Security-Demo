package com.yinzifan.security.dto;

import io.swagger.annotations.ApiModelProperty;

public class QueryCondition {

	@ApiModelProperty("用户名称")
	private String username;
	private String age;
	private String ageTo;
	private String xxx;
	public QueryCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QueryCondition(String username, String age, String ageTo, String xxx) {
		super();
		this.username = username;
		this.age = age;
		this.ageTo = ageTo;
		this.xxx = xxx;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAgeTo() {
		return ageTo;
	}
	public void setAgeTo(String ageTo) {
		this.ageTo = ageTo;
	}
	public String getXxx() {
		return xxx;
	}
	public void setXxx(String xxx) {
		this.xxx = xxx;
	}
	@Override
	public String toString() {
		return "QueryCondition [username=" + username + ", age=" + age + ", ageTo=" + ageTo + ", xxx=" + xxx + "]";
	}
	
	
	
}
