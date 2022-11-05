package com.bbms.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
		
//		Session session= Session.getInstance(props,new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(from, password);
//			}
//		});
//		
//		session.setDebug(true);

//		SimpleMailMessage msg = new SimpleMailMessage();

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try
        {
        	helper.setSubject("Hello");
    		helper.setFrom(from);
    		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    		message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to));
            
    		helper.setText("http://localhost:4200/home", true);
    		mailSender.send(message);
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
