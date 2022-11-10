package com.bbms.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			System.out.println(bean.getPassword());
			System.out.println(req.getServletContext().getAttribute("tempId"));
			service.changePassword(bean.getPassword(),(long) req.getServletContext().getAttribute("tempId"));
			req.getServletContext().removeAttribute("tempId");
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@PostMapping("/signup")
	public UserDto signup(@RequestBody UserDto bean){
		bean.setCreateAt(Date.valueOf(LocalDate.now()));
		bean.setUpdateAt(Date.valueOf(LocalDate.now()));
		UserDto usr = service.register(bean);
		return usr;
	}
	
	@PostMapping("/forgot_psw")
	public boolean forgotPassword(@RequestBody UserDto bean){
		System.out.println(bean.getEmail()+"is here");
			return service.generateTokenForResetPsw(bean.getEmail());
	}
	
}