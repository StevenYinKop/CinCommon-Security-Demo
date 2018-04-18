package com.yinzifan.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinzifan.service.HelloService;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object>{

	@Autowired
	private HelloService helloService;
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("MyConstraintValidator.initialize()");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// 校验逻辑
		helloService.greeting(value.toString());
		return false;
	}

}
