package com.bbms.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			String URL = null;
			if(invite.getUrl().equals("workspace"))
				URL="http://localhost:8080/api/v1/workspacejoin/"+invite.getWorkspaceId()+"/"+invite.getId();
			else if(invite.getUrl().equals("board"))
			    URL="http://localhost:8080/boardjoin/"+invite.getWorkspaceId()+"/"+invite.getId();
			boolean result=this.emailService.sendMail( invite,URL);
			
			if(result) {
				return ResponseEntity.ok(new MessageResponse("Email is Sent Successfully!"));
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Email not Sent!"));
			}
		}
		return ResponseEntity.ok(new MessageResponse("Insert Successfully!"));
		

	}
	
	@GetMapping("/workspacejoin/{id}/{userId}")
	public void joinWorkspace(@PathVariable long id,@PathVariable long userId, HttpServletResponse res) throws IOException{
		System.out.print("Id "+id);
		
		
			res.sendRedirect("http://localhost:4200/workspace/"+id);
		
	}
}
