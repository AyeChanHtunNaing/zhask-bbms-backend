package com.bbms.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.UserDto;
import com.bbms.service.UserService;


@RestController
public class AuthenticationController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/")
	public ResponseEntity<UserDto> login(Principal user){
		System.out.println("Name:"+user.getName());
		UserDto usr = service.getByEmail(user.getName());
		return ResponseEntity.ok(UserDto.builder().id(usr.getId()).name(usr.getName()).userName(usr.getUserName()).email(usr.getEmail()).createAt(usr.getCreateAt()).build());
	}
	
	@GetMapping("/verify/{id}/{token}")
	public boolean verifyEmail(@PathVariable long id,@PathVariable String token){
		return service.isTokenAvailable(id, token);
	}
	
	@GetMapping("/resetpsw/{id}/{token}")
	public boolean pswVerify(@PathVariable long id,@PathVariable String token, HttpServletRequest req){
		UserDto usr = service.isResetTokenAvailable(id, token);
		if(usr!=null) {
			req.getServletContext().setAttribute("tempId", usr.getId());
			return true;
		}else {			
			return false;
		}
	}
	
	@GetMapping("/resetpsw")
	public boolean pswVerify(@RequestBody UserDto bean, HttpServletRequest req){
		try {			
			service.changePassword(bean.getPassword(),(long) req.getServletContext().getAttribute("tempId"));
			req.getServletContext().removeAttribute("tempId");
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@PostMapping("/signup")
	public UserDto signup(@RequestBody UserDto bean){
		UserDto usr = service.register(bean);
		return usr;
	}
	
}
