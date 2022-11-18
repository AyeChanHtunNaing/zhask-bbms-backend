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
		verifyRegistration(email, usr.getId(), token,usr.getName());
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
			sendResetPasswordToken(email, usr.getId(), usr.getToken(),usr.getName());
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
	private void verifyRegistration(String recieverEmail, Long userId , String token,String userName){
		String subject = "Action Required: Please Confirm Your Email Address";
		String methodContent="Please Verify your email to use Bulletin Board System";
		sendMail(recieverEmail, "verify", userId , token, subject,userName,methodContent);
	}
	
	private void sendResetPasswordToken(String recieverEmail, Long userId , String token,String userName){
		String subject = "Reset Password Confirmation.";
		String methodContent="Press the button to reset password";
		sendMail(recieverEmail, "reset_psw", userId, token, subject,userName,methodContent);
	}
	
	private void sendMail(String recieverEmail, String route, Long userId , String token, String subject,String userName,String methodContent) {
		String mailBody="Dear "+userName+", <br>";
		mailBody+= methodContent+"<br>";
		mailBody += "<button style=\"text-decoration:none;background-color:#406595;color:white;width:300px;height:50px\"><a style=\"text-decoration:none;color:#fff;\"href=\""+SecurityContants.BACKEND_BASE_URL+"/"+route+"/"+userId+"/"+token+"/\">VERIFY</a></button>";	
		mailBody+="<br> With Best Regards, <br> Zhask";
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