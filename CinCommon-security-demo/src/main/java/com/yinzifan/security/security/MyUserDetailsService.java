package com.yinzifan.security.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService{

	private Logger logger= LoggerFactory.getLogger(getClass());
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("username = {}" , username);
		// 根据用户名查找用户信息
//		String username, String password, boolean enabled,
//		boolean accountNonExpired, boolean credentialsNonExpired,
//		boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
		String password = passwordEncoder.encode("123456");
		logger.info("password = {}" , password);
		return buildUser(username);
	}
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("username = {}" ,  userId);
		// 根据用户名查找用户信息
		return buildUser(userId);
	}
	private SocialUserDetails buildUser(String userId) {
		String password = passwordEncoder.encode("123456");
		logger.info("password = {}" , password);
		return new SocialUser( userId, //  userId
				password, // password
				true, // enabled
				true, // accountNonExpired
				true, // credentialsNonExpired
				true, // accountNonLocked
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin") // authorities
				);
	}

}
