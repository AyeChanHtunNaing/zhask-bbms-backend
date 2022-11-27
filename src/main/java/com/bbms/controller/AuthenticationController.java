package com.bbms.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bbms.config.SecurityContants;
import com.bbms.dto.NotificationDto;
import com.bbms.dto.UserDto;
import com.bbms.model.MessageResponse;
import com.bbms.model.User;
import com.bbms.service.NotificationService;
import com.bbms.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@EnableAutoConfiguration
public class AuthenticationController {
	
	@Autowired
	UserService userService;

	@Autowired
	NotificationService notiService;
	
	@GetMapping("/")
	public ResponseEntity<UserDto> login(Principal user){
//		UserDto usr = userService.getByEmail(user.getName());
//		UserDto loginUsr = UserDto.builder().id(usr.getId()).name(usr.getName()).userName(usr.getUserName()).email(usr.getEmail()).createAt(usr.getCreateAt()).build();
////		loginUsr.setNotification(notiService.getNotificationByUserId(usr.getId()));
//		for(NotificationDto tempdto : usr.getNotification()) {
//			System.out.println(tempdto.getId()+":"+tempdto.getContent());
//		}
//		return ResponseEntity.ok(loginUsr);
		
		UserDto usr = userService.getByEmail(user.getName());
		return ResponseEntity.ok(UserDto.builder().id(usr.getId()).name(usr.getName()).userName(usr.getUserName()).email(usr.getEmail()).createAt(usr.getCreateAt()).build());
	
	}
	
	@GetMapping("/verify/{userId}/{token}")
	public void verifyEmail(@PathVariable Long userId,@PathVariable String token, HttpServletResponse res) throws IOException{
		System.out.print("Id "+userId+" token "+token);
		
		if(userService.isTokenAvailable(userId, token)) {
			res.sendRedirect(SecurityContants.FRONTEND_BASE_URL+"/activated-account"); 
		}
		else {
			res.sendRedirect(SecurityContants.FRONTEND_BASE_URL+"/token-expired");
		}
	}
	
	@GetMapping("/reset_psw/{userId}/{token}")
	public void pswVerify(@PathVariable Long userId,@PathVariable String token, HttpServletRequest req, HttpServletResponse res) throws IOException{
		UserDto usr = userService.isResetTokenAvailable(userId, token);
		if(usr!=null) {
			req.getServletContext().setAttribute("tempId", usr.getId());
			res.sendRedirect(SecurityContants.FRONTEND_BASE_URL+"/reset-password");
		}else {
			res.sendRedirect(SecurityContants.FRONTEND_BASE_URL+"/token-expired");
		}
	}
	
	@PostMapping("/reset_psw")
	public boolean pswVerify(@RequestBody UserDto dto, HttpServletRequest req){
		try {			
			userService.changePassword(dto.getPassword(),(Long) req.getServletContext().getAttribute("tempId"));
			req.getServletContext().removeAttribute("tempId");
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@PostMapping(value="/signup",produces="application/json")
	public UserDto signup(@RequestBody User user){
		UserDto userDto=new UserDto();
		userDto.setName(user.getName());
		userDto.setUserName(user.getUserName());
		userDto.setEmail(user.getEmail());
		userDto.setCreateAt(LocalDate.now());
		userDto.setUpdateAt(LocalDate.now());
		userDto.setPassword(user.getPassword());
		UserDto usr = userService.register(userDto);
		return usr;
	}
	
	@PutMapping("/updateprofile")
	public ResponseEntity<Boolean> updateProfile(@RequestParam("userid") String userId,@RequestParam("username") String username,@RequestParam("name") String name,@RequestParam(value = "file", required = false)  MultipartFile file) throws JsonMappingException, JsonProcessingException{
		UserDto userDto=new UserDto();
		userDto.setId(Long.parseLong(userId));
		userDto.setName(name);
		userDto.setUserName(username);
	    try {
			userDto.setProfile(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("third"+e);
			e.printStackTrace();
		}
		userDto.setPictureName(file.getOriginalFilename());
		userService.updateProfile(userDto);
		 return ResponseEntity.ok(true);
	}
	
	@PostMapping("/forgot_psw")
	public boolean forgotPassword(@RequestBody UserDto dto){
			return userService.generateTokenForResetPsw(dto.getEmail());
	}
	
	@GetMapping(value="/showUserNameByUserId/{userId}",produces="application/json")
	public UserDto generateUserNameByUserId(@PathVariable Long userId) {
		return userService.showUserNameByUserId(userId);
	}
	
//	@GetMapping("/logout")
//	public void logout(){
////		response.reset();
//		SecurityContextHolder.clearContext();
//		System.out.println("++true++");
////		return ResponseEntity.ok(new MessageResponse("Successfully Logout!"));	
//	}
	
	@GetMapping("/temp_logout")
	public ResponseEntity<?> clear(HttpServletResponse response) {
		response.reset();
		SecurityContextHolder.clearContext();
		System.out.println("++true++");
		return ResponseEntity.ok(new MessageResponse("Successfully Logout!"));	
	}
	
}