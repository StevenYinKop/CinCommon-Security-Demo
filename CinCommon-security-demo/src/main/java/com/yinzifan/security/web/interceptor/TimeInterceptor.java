package com.yinzifan.security.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class TimeInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("TimeInterceptor.preHandle()");
		request.setAttribute("startTime", System.currentTimeMillis());
		
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		System.out.println(handlerMethod.getBean().getClass().getName());
		System.out.println(handlerMethod.getMethod().getName());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long start = (long)request.getAttribute("startTime");
		System.out.println("TimeInterceptor.postHandle() spend: "+ (System.currentTimeMillis() - start) + "ms");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long start = (long)request.getAttribute("startTime");
		System.out.println("TimeInterceptor.afterCompletion() spend: "+ (System.currentTimeMillis() - start) + "ms");
		System.out.println("ex is :" + ex);
	}

}
