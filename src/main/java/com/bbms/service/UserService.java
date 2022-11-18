package com.bbms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bbms.config.SecurityContants;
import com.bbms.dto.UserDto;
import com.bbms.repository.UserRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private EmailService mailservice;
	
	@Autowired
	private PasswordEncoder encdr;
	
	public UserDto register(UserDto dto){ 
		String email = dto.getEmail();
		String name = dto.getName();
		String uname = dto.getName();
		String pass = encdr.encode(dto.getPassword());
		LocalDate createAt = dto.getCreateAt();
		String token = RandomString.make(64);
		UserDto usr = UserDto.builder().email(email).name(name).userName(uname).password(pass).token(token).status(false).createAt(createAt).build();
		try {
			usr = userRepository.save(usr);	
		}catch(DataIntegrityViolationException e){
			return UserDto.builder().email("false").build();
		}
		verifyRegistration(email, usr.getId(), token);
		return usr;
	}
	
	public UserDto getById(Long userId) {
		return userRepository.getById(userId);
	}
	
	public UserDto getByEmail(String mail) {
		return userRepository.getByEmail(mail);
	}
	
	public UserDto changePassword(String psw,Long userId){
		UserDto usr = userRepository.getById(userId);
		usr.setPassword(encdr.encode(psw));
		usr.setToken(usr.getName());
		return userRepository.save(usr);
	}
	
	public boolean generateTokenForResetPsw(String email){
		UserDto usr = userRepository.findByEmail(email);
		if(usr!=null) {
			String token = RandomString.make(64);
			usr.setToken(token);
			sendResetPasswordToken(email, usr.getId(), usr.getToken());
			userRepository.save(usr);
			return true; 
		}else {
			return false;
		}
	}
	
	//methods to validate token.
	public boolean isTokenAvailable(Long userId,String token){
		try {
			if(token.equals(userRepository.getTokenById(userId))) {
				UserDto usr = (UserDto) userRepository.getById(userId);
				 usr.setToken(usr.getName());
				 usr.setStatus(true);
				 userRepository.save(usr);
				return true;
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
		return false;			
	}
	
	public UserDto isResetTokenAvailable(Long userId,String token){
		try {
			if(token.equals(userRepository.getTokenById(userId))) {
				return userRepository.getById(userId);
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;			
	}
	//methods to validate token.
	
	//methods to send mail.
	private void verifyRegistration(String recieverEmail, Long userId , String token){
		String subject = "Verify Your Account.";
		sendMail(recieverEmail, "verify", userId , token, subject);
	}
	
	private void sendResetPasswordToken(String recieverEmail, Long userId , String token){
		String subject = "Reset Password Confirmation.";
		sendMail(recieverEmail, "reset_psw", userId, token, subject);
	}
	
	private void sendMail(String recieverEmail, String route, Long userId , String token, String subject) {
		String mailBody="Dear User,\n"
				+ "\n"
				+ "Please Verify to use Bulletin Board System \n";
		 mailBody += "<button style=\\\"text-decoration:none;background-color:#406595;color:white;\\\"><a href=\""+SecurityContants.BACKEND_BASE_URL+"/"+route+"/"+userId+"/"+token+"/\">VERIFY</a></button>";
		 mailBody += "\n With Best Regards,\n"
		 		+ "Zhask";
		try {			
			mailservice.sendEmailWithMimeMessage(recieverEmail, mailBody, subject);
		}catch (Exception e) {
			System.out.println("Failed");
		}
	}
	//methods to send mail.
	
	public UserDto showUserNameByUserId(Long userId) {
		return userRepository.getById(userId);
	}
	
	public void updateProfile(UserDto dto)
	{
		userRepository.updateUser(dto.getName(), dto.getUserName(), dto.getProfile(), dto.getPictureName(), dto.getId());
	}
}