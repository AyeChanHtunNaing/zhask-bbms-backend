package com.bbms.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	
	
	public boolean sendMail(String to) throws MessagingException{
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
		
		Session session= Session.getInstance(props,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		
		session.setDebug(true);
		
	
		SimpleMailMessage msg = new SimpleMailMessage();
		
		
//		MimeMessage m=new MimeMessage(session);
//		try {
//			m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//			msg.setSubject("Hello");
//			msg.setText("http://localhost:4200/home");
//			msg.se
//			mailSender.send(msg);
//			f=true;
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try
        {
        	helper.setSubject("Hello");
    		helper.setFrom(from);
    		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    		message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to));

    		helper.setText("http://localhost:4200/home", true);
        }
        catch(Exception e)
        {
        	
        }
		
		mailSender.send(message);
	return f;
	}   
}