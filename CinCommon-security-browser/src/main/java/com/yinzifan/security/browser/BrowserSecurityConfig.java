package com.yinzifan.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.yinzifan.security.browser.authentication.CustAuthenticationFailureHandler;
import com.yinzifan.security.browser.authentication.CustAuthenticationSuccessHandler;
import com.yinzifan.security.core.properties.SecurityProperties;
import com.yinzifan.security.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private CustAuthenticationSuccessHandler custAuthenticationSuccessHandler; 
	
	@Autowired
	private CustAuthenticationFailureHandler custAuthenticationFailureHandler;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(custAuthenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin() // 用表单登录进行身份认证
				.successHandler(custAuthenticationSuccessHandler)
				.failureHandler(custAuthenticationFailureHandler)
				.loginPage("/authentication/require")
				.loginProcessingUrl("/authentication/form")
			.and() // 
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSec())
				.userDetailsService(userDetailsService)
			.and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/authentication/require", 
							securityProperties.getBrowser().getLoginPage(), 
							"/code/*")
							.permitAll()
				.anyRequest() // 任何请求
				.authenticated(); // 都要进行身份认证
			
	}
}
