package com.bbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bbms.dto.UserDto;
import com.bbms.repository.UserRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	private EmailService mailservice;
	
	@Autowired
	private PasswordEncoder encdr;
	
	public UserDto register(UserDto user){
//		long id = user.getId(); 
		String email = user.getEmail();
		String name = user.getName();
		String uname = user.getName();
		String pass = encdr.encode(user.getPassword());
		String token = RandomString.make(64);
//		if (checkmailservice.emailExists(id, email)) {
//			return UserDto.builder().email("false").build();
//		}
		UserDto usr = UserDto.builder().email(email).name(name).userName(uname).password(pass).token(token).status(false).build();
		try {
			usr = repo.save(usr);	
		}catch(DataIntegrityViolationException e){
			return UserDto.builder().email("false").build();
		}
		verifyRegistration(email, usr.getId(), token);
		return usr;
	}
	
	public UserDto changePassword(String psw, long id){
		UserDto usr = repo.getById(id);
		usr.setPassword(encdr.encode(psw));
		return repo.save(usr);
	}
	
	//reset start from this method
	public void generateTokenForResetPsw(String email){
		UserDto usr = repo.findByEmail(email);
		if(usr!=null) {
			String token = RandomString.make(64);
			usr.setToken(token);
			repo.save(usr);
			sendResetPasswordToken(email, usr.getId(), usr.getToken());
		}else {
			System.out.println("User do not exists!");
		}
	}
	
	//methods to validate token.
	public boolean isTokenAvailable(long id,String token){
		try {
			if(token.equals(repo.getTokenById(id))) {
				UserDto usr = (UserDto) repo.getById(id);
				 usr.setToken(usr.getName());
				 usr.setStatus(true);
				 repo.save(usr);
				return true;
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
		return false;			
	}
	
	public UserDto isResetTokenAvailable(long id,String token){
		try {
			if(token.equals(repo.getTokenById(id))) {
				return repo.getById(id);
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;			
	}
	//methods to validate token.
	
	//methods to send mail.
	private void verifyRegistration(String recieverEmail, long id, String token){
		String subject = "Verify Your Account.";
		sendMail(recieverEmail, "verify", id, token, subject);
	}
	
	private void sendResetPasswordToken(String recieverEmail, long id, String token){
		String subject = "Reset Password Confirmation.";
		sendMail(recieverEmail, "resetpsw", id, token, subject);
	}
	
	private void sendMail(String recieverEmail, String route, long id, String token, String subject) {
		String mailBody = "<a href=\"http://localhost:8080/"+route+"/"+id+"/"+token+"/\"><button style=\"text-decoration:none;background-color:#406595;color:white;\">VERIFY</button></a>";
		try {			
			mailservice.sendEmailWithMimeMessage(recieverEmail, mailBody, subject);
		}catch (Exception e) {
			System.out.println("Failed");
		}
	}
	//methods to send mail.

}