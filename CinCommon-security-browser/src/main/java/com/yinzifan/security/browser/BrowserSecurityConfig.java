package com.yinzifan.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.yinzifan.security.core.authentication.AbstractChannelSecurityConfig;
import com.yinzifan.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yinzifan.security.core.properties.SecurityConstants;
import com.yinzifan.security.core.properties.SecurityProperties;
import com.yinzifan.security.core.validate.code.ValidateCodeSecurityConfig;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer socialConfig;
	
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

		applyPasswordAuthenticationConfig(http);		
		http
	.apply(validateCodeSecurityConfig)
		.and()
	.apply(smsCodeAuthenticationSecurityConfig)
		.and()
	.apply(socialConfig)
		.and()
	.rememberMe()
		.tokenRepository(persistentTokenRepository())
		.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
		.userDetailsService(userDetailsService)
		.and()
	.authorizeRequests()
		.antMatchers(
			SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
			SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
			securityProperties.getBrowser().getLoginPage(),
			SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*")
			.permitAll()
		.anyRequest()
		.authenticated()
		.and()
	.csrf().disable();
//		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//		validateCodeFilter.setAuthenticationFailureHandler(custAuthenticationFailureHandler);
//		validateCodeFilter.setSecurityProperties(securityProperties);
//		validateCodeFilter.afterPropertiesSet();
//		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//			.formLogin() // 用表单登录进行身份认证
//				.successHandler(custAuthenticationSuccessHandler)
//				.failureHandler(custAuthenticationFailureHandler)
//				.loginPage("/authentication/require")
//				.loginProcessingUrl("/authentication/form")
//			.and() // 
//			.rememberMe()
//				.tokenRepository(persistentTokenRepository())
//				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSec())
//				.userDetailsService(userDetailsService)
//			.and()
//				.csrf().disable()
//				.authorizeRequests()
//				.antMatchers("/authentication/require", 
//							securityProperties.getBrowser().getLoginPage(), 
//							"/code/*")
//							.permitAll()
//				.anyRequest() // 任何请求
//				.authenticated(); // 都要进行身份认证
//			
		
		
	}
}
