package com.bbms.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.model.InviteMember;
import com.bbms.model.MessageResponse;
import com.bbms.service.EmailService;

@RestController
@RequestMapping("/api/v1/")
public class InviteController {
	@Autowired
	private EmailService emailService;
	@PostMapping(value = "/invite", produces = "application/json")
	public ResponseEntity<?> inviteMember(@RequestBody InviteMember invite) throws MessagingException  {
		
		
	
		if(invite.getEmail()!=null)
		{
			boolean result=this.emailService.sendMail( invite.getEmail());
			
			if(result) {
				return ResponseEntity.ok(new MessageResponse("Email is Sent Successfully!"));
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Email not Sent!"));
			}
		}
		return ResponseEntity.ok(new MessageResponse("Insert Successfully!"));
		

	}
}
