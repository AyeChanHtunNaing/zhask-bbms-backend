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

import com.bbms.config.SecurityContants;
import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskDto;
import com.bbms.dto.UserDto;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.InviteMember;
import com.bbms.model.MessageResponse;
import com.bbms.repository.BoardRepository;
import com.bbms.repository.UserRepository;
import com.bbms.service.BoardService;
import com.bbms.service.EmailService;
import com.bbms.service.NotificationService;
import com.bbms.service.TaskService;
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
	TaskService taskService;

	@Autowired
	BoardService boardService;

	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notiService;

	@PostMapping(value = "/invite", produces = "application/json")
	public ResponseEntity<?> inviteMember(@RequestBody InviteMember invite) throws MessagingException {
		
			String URL = null;
			if (invite.getUrl().equals("workspace"))
				URL = SecurityContants.BACKEND_BASE_URL + "/api/v1/workspacejoin/" + invite.getWorkspaceId() + "/"
						+ invite.getId();
			else if (invite.getUrl().equals("board"))
				URL = SecurityContants.BACKEND_BASE_URL + "/api/v1/boardjoin/" + invite.getWorkspaceId() + "/"
						+ invite.getId();
			else if (invite.getUrl().equals("task")) {
				UserDto user = userRepository.findByEmail(invite.getEmail());
				if (user == null || boardService.isExitUserIdInBoard(user.getId()) == null) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body(new MessageResponse("Email not sent!"));
				} else {
					URL = SecurityContants.BACKEND_BASE_URL + "/api/v1/taskjoin/" + invite.getWorkspaceId() + "/"
							+ invite.getId();
				}

			}
			boolean result = this.emailService.sendMail(invite, URL);

			if (result) {
				return ResponseEntity.ok(new MessageResponse("Email is Sent Successfully!"));
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new MessageResponse("Email not Sent!"));
			}
		
	}

	@GetMapping("/workspacejoin/{workspaceId}/{userId}/{email}")
	public void joinWorkspace(@PathVariable Long workspaceId, @PathVariable Long userId, @PathVariable String email,
			HttpServletResponse res) throws IOException {
		UserDto user = userRepository.findByEmail(email);
		if (user == null) {
			res.sendRedirect(SecurityContants.FRONTEND_BASE_URL + "/login");
		} else {
			WorkspaceDto dto = workspaceService.isExistWorkspace(workspaceId, user.getId());
			if (dto == null) {
				List<BoardDto> boardList = boardService.getBoardRelatedWorkspace(workspaceId, userId);
				for (int i = 0; i < boardList.size(); i++) {
					addBoard(boardList.get(i).getId(), user);
				}
				WorkspaceDto tempWorkspace = addWorkspace(workspaceId, user);
				notiService.createNoti(tempWorkspace.getCreatedBy(), userService.getById(userId).getName(), userService.getByEmail(email).getName(), "Workspace:"+tempWorkspace.getName());
			}
			res.sendRedirect(SecurityContants.FRONTEND_BASE_URL + "/home");
		}
	}

	private WorkspaceDto addWorkspace(Long id, UserDto user) {
		WorkspaceDto workspaceDto = workspaceService.getWorkspaceIdByWorkspace(id);
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		workspaceDto.getUsers().add(userDto);
		return workspaceService.insert(workspaceDto);
	}

	@GetMapping("/boardjoin/{boardId}/{userId}/{email}")
	public void joinBoard(@PathVariable Long boardId, @PathVariable Long userId, @PathVariable String email,
			HttpServletResponse res) throws IOException {
		UserDto user = userRepository.findByEmail(email);
		if (user == null) {
			res.sendRedirect("http://localhost:4200/login");
		} else {
			BoardDto dto = boardService.isExistBoard(boardId, user.getId());
			if (dto == null) {
				addBoard(boardId, user);
				BoardDto board = boardService.getBoardByBoardId(boardId);
				addWorkspace(board.getWorkspace().getId(), user);
				notiService.createNoti(board.getCreatedBy(), userService.getById(userId).getName(), userService.getByEmail(email).getName(), "Board:"+board.getName());
				res.sendRedirect(SecurityContants.FRONTEND_BASE_URL + "/workspace/" + board.getWorkspace().getId());
			} else {
				BoardDto board = boardService.getBoardByBoardId(boardId);
				notiService.createNoti(board.getCreatedBy(), userService.getById(userId).getName(), userService.getByEmail(email).getName(), "Board:"+board.getName());
				res.sendRedirect(SecurityContants.FRONTEND_BASE_URL + "/workspace/" + board.getWorkspace().getId());
			}

		}
	}

	private void addBoard(long id, UserDto user) {

		BoardDto boardDto = boardService.getBoardByBoardId(id);
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		boardDto.getUsers().add(userDto);
		boardService.insert(boardDto);
	}

	@GetMapping("/taskjoin/{taskId}/{userId}/{email}")
	public void joinTask(@PathVariable Long taskId, @PathVariable Long userId, @PathVariable String email,
			HttpServletResponse res) throws IOException {
		UserDto user = userRepository.findByEmail(email);
		TaskDto taskDto = taskService.getTaskbyId(taskId);
		TaskDto dto = taskService.checkTaskHasUser(taskId, userId);
		if (dto == null) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			taskDto.getUsers().add(userDto);
			TaskDto tempTask = taskService.insert(taskDto);
			notiService.createNoti(tempTask.getCreatedBy(), userService.getById(userId).getName(), userService.getByEmail(email).getName(), "Task:"+tempTask.getDescription());
			res.sendRedirect(SecurityContants.FRONTEND_BASE_URL + "/board/" + taskDto.getTaskList().getId());
		} else {
			res.sendRedirect(SecurityContants.FRONTEND_BASE_URL + "/board/" + taskDto.getTaskList().getId());
		}

	}
	
}
