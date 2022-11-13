package com.bbms.controller;

import java.io.IOException;
import java.util.List;

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

import com.bbms.dto.BoardDto;
import com.bbms.dto.UserDto;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.InviteMember;
import com.bbms.model.MessageResponse;
import com.bbms.repository.UserRepository;
import com.bbms.service.BoardService;
import com.bbms.service.EmailService;
import com.bbms.service.UserService;
import com.bbms.service.WorkspaceService;

@RestController
@RequestMapping("/api/v1/")
public class InviteController {
	@Autowired
	private EmailService emailService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	WorkspaceService workspaceService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/invite", produces = "application/json")
	public ResponseEntity<?> inviteMember(@RequestBody InviteMember invite) throws MessagingException  {
		if(invite.getEmail()!=null)
		{ 
			String URL = null;
			if(invite.getUrl().equals("workspace"))
				URL="http://localhost:8080/api/v1/workspacejoin/"+invite.getWorkspaceId()+"/"+invite.getId();
			else if(invite.getUrl().equals("board"))
			    URL="http://localhost:8080/api/v1/boardjoin/"+invite.getWorkspaceId()+"/"+invite.getId();
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
	
	@GetMapping("/workspacejoin/{id}/{userId}/{email}")
	public void joinWorkspace(@PathVariable long id,@PathVariable long userId,@PathVariable String email, HttpServletResponse res) throws IOException{
		UserDto user=userRepository.findByEmail(email);
		if(user==null)
		{
			res.sendRedirect("http://localhost:4200/login");
		}
		else
		{
			WorkspaceDto workspaceDto=workspaceService.getWorkspaceIdByWorkspace(id);
			UserDto userDto=new UserDto();
			userDto.setId(user.getId());
			workspaceDto.getUsers().add(userDto);
			workspaceService.insert(workspaceDto);
			res.sendRedirect("http://localhost:4200/workspace/"+id);
		}	
	}
	
	@GetMapping("/boardjoin/{id}/{userId}/{email}")
	public void joinBoard(@PathVariable long id,@PathVariable long userId,@PathVariable String email, HttpServletResponse res) throws IOException{
		UserDto user=userRepository.findByEmail(email);
		if(user==null)
		{
			res.sendRedirect("http://localhost:4200/login");
		}
		else
		{
			BoardDto boardDto=boardService.getBoardByBoardId(id);
			UserDto userDto=new UserDto();
			userDto.setId(user.getId());
			boardDto.getUsers().add(userDto);
			boardService.insert(boardDto);
			res.sendRedirect("http://localhost:4200/board/"+id);
		}	
	}
}
