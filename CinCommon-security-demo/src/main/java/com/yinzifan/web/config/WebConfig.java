package com.yinzifan.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yinzifan.web.interceptor.TimeInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private TimeInterceptor timeIntercepter;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeIntercepter);
		super.addInterceptors(registry);
	}

//	@Bean
	/**
	 * 这种配置方式也可以注册过滤器, 就像在TimeFilter类上面添加一个@Component一样.
	 * 区别在于, 这种方式可以指定具体拦截的url, 而@Component会对所有的url起拦截作用
	 */
//	public FilterRegistrationBean timeFilter() {
//		FilterRegistrationBean bean = new FilterRegistrationBean();
//		TimeFilter timeFilter = new TimeFilter();
//		bean.setFilter(timeFilter);
//		List<String> url = new ArrayList<>();
//		url.add("/*");
//		bean.setUrlPatterns(url);
//		return bean;
//	}
	
}
