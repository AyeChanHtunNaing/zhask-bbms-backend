package com.bbms.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bbms.model.InviteMember; 
@Service
public class EmailService {

	public boolean sendMail(InviteMember invitemember,String URL) throws MessagingException{
		boolean f=false;
		
		String from="team.zhask.dev@gmail.com";
		String password="jwsspbhphfmybuhd";
	
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername(from);
	    mailSender.setPassword(password);
	   
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try
        {
        	String email=invitemember.getEmail();
        	String []emails=email.split(",");
        	for(int i=0;i<emails.length;i++)
        	{
        	 String content = "Dear Friend,<br>Please click the link below to join my "+invitemember.getUrl()+":<br><h3><a href=\"[[URL]]\" target=\"_self\">JOIN   "+ invitemember.getUrl().substring(0, 1).toUpperCase() +invitemember.getUrl() .substring(1)+"</a></h3>Thank you,<br>";
        	 content = content.replace("[[URL]]",URL+"/"+emails[i]);
        	helper.setSubject("Dear Friend,Please Join My "+invitemember.getUrl());
    		helper.setFrom(from,invitemember.getName());
    		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emails[i]));
    		message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emails[i]));
            
    		helper.setText(content, true);
    		mailSender.send(message);
        	}
    		f=true;
        }
        catch(Exception e)
        {
        	f=false;
        }
		
		
	return f;
	}
	
	public void sendEmailWithMimeMessage(String toEmail, String body, String subject)
			throws MessagingException {
		String from="team.zhask.dev@gmail.com";
		String password="jwsspbhphfmybuhd";
	
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername(from);
	    mailSender.setPassword(password);
	   
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("ZHASK");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body,true);
		mimeMessageHelper.setSubject(subject);

		mailSender.send(mimeMessage);
		System.out.println("Mail Send...");

	}
}
