package com.bbms.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bbms.model.InviteMember;
import com.bbms.model.Noti; 
@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;

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

		try {
			mimeMessageHelper.setFrom(from,"Bulletin Board System");
		
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body,true);
		mimeMessageHelper.setSubject(subject);

		mailSender.send(mimeMessage);
		System.out.println("Mail Send...");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void mailWithSimpleMailMessage(String toEmail,String body,String subject) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("team.zhask.dev@gmail.com");
		message.setTo(toEmail);
		String mailSubject=subject;
		String mailContent=body;
		message.setSubject(mailSubject);
		message.setText(mailContent);
        mailSender.send(message);		
		
		
	}
	
	public void notiEmail(String sender,List<Noti> list) throws MessagingException
	{
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
        
        	for(int i=0;i<list.size();i++)
        	{
        	 String content = "Dear [[name]],<br> [[create]] [[cardname]] <br>Thank you";
        	 content = content.replace("[[name]]",list.get(i).getName());
        	 content = content.replace("[[create]]",sender);
        	 content = content.replace("[[cardname]]",list.get(i).getContent());
        	helper.setSubject("Notification ");
    		helper.setFrom(from,"Zhask");
    		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(list.get(i).getEmail()));
    		message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(list.get(i).getEmail()));
            
    		helper.setText(content, true);
    		mailSender.send(message);
        	}
    		
        }
        catch(Exception e)
        {
        	
        }
		
	}
}
