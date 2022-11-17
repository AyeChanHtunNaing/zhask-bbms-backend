package com.bbms.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.UserDto;
import com.bbms.model.User;
import com.bbms.service.UserService;


@RestController
public class AuthenticationController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/")
	public ResponseEntity<UserDto> login(Principal user){
		//System.out.println("Name:"+user.getName());
		UserDto usr = service.getByEmail(user.getName());
		return ResponseEntity.ok(UserDto.builder().id(usr.getId()).name(usr.getName()).userName(usr.getUserName()).email(usr.getEmail()).createAt(usr.getCreateAt()).build());
	}
	
	@GetMapping("/verify/{id}/{token}")
	public void verifyEmail(@PathVariable long id,@PathVariable String token, HttpServletResponse res) throws IOException{
		System.out.print("Id "+id+" token "+token);
		
		if(service.isTokenAvailable(id, token)) {
			res.sendRedirect("http://localhost:4200/activated-account"); 
		}
		else {
			res.sendRedirect("http://localhost:4200/token-expired");
		}
	}
	
	@GetMapping("/reset_psw/{id}/{token}")
	public void pswVerify(@PathVariable long id,@PathVariable String token, HttpServletRequest req, HttpServletResponse res) throws IOException{
		UserDto usr = service.isResetTokenAvailable(id, token);
		if(usr!=null) {
			req.getServletContext().setAttribute("tempId", usr.getId());
			res.sendRedirect("http://localhost:4200/reset-password");
		}else {
			res.getWriter().write("Invalid-Token");
//			res.sendRedirect("http://localhost:4200/token-expired");
		}
	}
	
	@PostMapping("/reset_psw")
	public boolean pswVerify(@RequestBody UserDto bean, HttpServletRequest req){
		try {			
			service.changePassword(bean.getPassword(),(long) req.getServletContext().getAttribute("tempId"));
			req.getServletContext().removeAttribute("tempId");
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@PostMapping(value="/signup",produces="application/json")
	public UserDto signup(@RequestBody User bean){
		UserDto userDto=new UserDto();
		userDto.setName(bean.getName());
		userDto.setUserName(bean.getUserName());
		userDto.setEmail(bean.getEmail());
		userDto.setCreateAt(LocalDate.now());
		userDto.setUpdateAt(LocalDate.now());
		userDto.setPassword(bean.getPassword());
		UserDto usr = service.register(userDto);
		return usr;
	}
	
	@PutMapping("/updateprofile")
	public ResponseEntity<Boolean> updateProfile(@RequestBody User bean){
		UserDto userDto=new UserDto();
		userDto.setId(bean.getId());
		userDto.setName(bean.getName());
		userDto.setUserName(bean.getUserName());
		userDto.setEmail(bean.getEmail());
		userDto.setCreateAt(bean.getCreateAt());
		userDto.setProfile(bean.getProfile().getBytes());
		service.updateProfile(userDto);
		 return ResponseEntity.ok(true);
	}
	
	@PostMapping("/forgot_psw")
	public boolean forgotPassword(@RequestBody UserDto bean){
			return service.generateTokenForResetPsw(bean.getEmail());
	}
	
	@GetMapping(value="/showUserNameByUserId/{userId}",produces="application/json")
	public UserDto generateUserNameByUserId(@PathVariable Long userId) {
		return service.showUserNameByUserId(userId);
	}
	
}