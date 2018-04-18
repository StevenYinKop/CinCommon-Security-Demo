package com.yinzifan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.yinzifan.dto.QueryCondition;
import com.yinzifan.dto.User;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	public List<User> query(QueryCondition query,@PageableDefault(sort= {"username", "asc"}) Pageable pageable ) {
		LOGGER.info("QueryCondition: {}", ReflectionToStringBuilder.toString(query, ToStringStyle.MULTI_LINE_STYLE));
		LOGGER.info("pageable: {}", ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable("id")String id) {
		User user = new User();
		user.setUsername("tom");
		return user;
	}
	
	@PostMapping
	public User create(@RequestBody@Valid User user, BindingResult error) {
		if(error.hasErrors()) {
			error.getAllErrors().stream().forEach(x -> System.out.println(x.getDefaultMessage()));
		}
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult error) {
		if(error.hasErrors()) {
			error.getAllErrors().stream().forEach(x -> {
//					FieldError fieldError = (FieldError)x;
//					String field = fieldError.getField();
//					System.out.println(field + ": " + x.getDefaultMessage());
					System.out.println(x.getDefaultMessage());
				});
		}
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable("id")String id) {
		System.out.println(id);
	}
}

