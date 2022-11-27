package com.bbms.controller;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.model.Noti;
import com.bbms.service.EmailService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1/")
public class NotiEmailController {
//	@Autowired
//	private BoardService boardService;
//	
//	@Autowired
//	private UserRepository userRepository;
//	
	@Autowired
	private EmailService emailService;
	
	
	@PostMapping(value = "/notiemail/{name}", produces = "application/json")
	public boolean notiEmailSending(@PathVariable String name,@RequestBody List<Noti> notis) throws MessagingException {
	 System.out.println("hello"+name+"email"+notis);
	  emailService.notiEmail(name, notis);
      return true;
}
}