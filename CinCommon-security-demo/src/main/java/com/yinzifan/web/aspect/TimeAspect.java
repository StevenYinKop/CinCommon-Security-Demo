package com.yinzifan.web.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
//	@Before
//	@AfterReturning
//	@After
//	@AfterThrow
	@Around("execution(* com.yinzifan.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("TimeAspect.handleControllerMethod() start");
		Object result = pjp.proceed();
		Object[] args = pjp.getArgs();
		Arrays.stream(args).forEach(x -> x.toString());
		System.out.println("TimeAspect.handleControllerMethod() end");
		return result;
	}
}
 