package com.yinzifan.security.core.authentication.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SmsCodeAuthenticationFilter  extends
AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers
	// =====================================================================================
	
	public static final String SECURITY_MOBILE_KEY = "mobile";
	//public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
	
	private String mobileParameter = SECURITY_MOBILE_KEY;
	private boolean postOnly = true;
	
	// ~ Constructors
	// ===================================================================================================
	
	public SmsCodeAuthenticationFilter() {
	super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
	}
	
	// ~ Methods
	// ========================================================================================================
	
	public Authentication attemptAuthentication(HttpServletRequest request,
		HttpServletResponse response) throws AuthenticationException {
	if (postOnly && !request.getMethod().equals("POST")) {
		throw new AuthenticationServiceException(
				"Authentication method not supported: " + request.getMethod());
	}
	
	String mobile = obtainMobile(request);
	//String password = obtainPassword(request);
	
	if (mobile == null) {
		mobile = "";
	}
	
	
	mobile = mobile.trim();
	
	SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(
			mobile);
	
	// Allow subclasses to set the "details" property
	setDetails(request, authRequest);
	
	return this.getAuthenticationManager().authenticate(authRequest);
	}
	protected String obtainMobile(HttpServletRequest request) {
	return request.getParameter(mobileParameter);
	}
	
	public String getMobileParameter() {
		return mobileParameter;
	}
	
	public void setMobileParameter(String mobileParameter) {
		this.mobileParameter = mobileParameter;
	}
	
	/**
	* Provided so that subclasses may configure what is put into the authentication
	* request's details property.
	*
	* @param request that an authentication request is being created for
	* @param authRequest the authentication request object that should have its details
	* set
	*/
	protected void setDetails(HttpServletRequest request,
		SmsCodeAuthenticationToken authRequest) {
	authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
	
	
	/**
	* Defines whether only HTTP POST requests will be allowed by this filter. If set to
	* true, and an authentication request is received which is not a POST request, an
	* exception will be raised immediately and authentication will not be attempted. The
	* <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
	* authentication.
	* <p>
	* Defaults to <tt>true</tt> but may be overridden by subclasses.
	*/
	public void setPostOnly(boolean postOnly) {
	this.postOnly = postOnly;
	}

}
