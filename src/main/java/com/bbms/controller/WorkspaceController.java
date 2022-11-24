package com.bbms.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.BoardDto;
import com.bbms.dto.UserDto;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.MessageResponse;
import com.bbms.model.User;
import com.bbms.model.Workspace;
import com.bbms.service.BoardService;
import com.bbms.service.TaskService;
import com.bbms.service.WorkspaceService;

@RestController
@RequestMapping("/api/v1/")
public class WorkspaceController {

	@Autowired
	private WorkspaceService workspaceService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private BoardService boardService;

	@PostMapping(value = "/workspace", produces = "application/json")
	public ResponseEntity<?> createWorkspacec(@RequestBody Workspace workspace) {

		WorkspaceDto workspaceDto = new WorkspaceDto();
		workspaceDto.setName(workspace.getName());
		workspaceDto.setDescription(workspace.getDescription());
		workspaceDto.setCreatedBy(workspace.getCreatedBy());
		List<UserDto> dtoList = new ArrayList<UserDto>();
		UserDto usrDto = new UserDto();
		User user = workspace.getUsers().get(0);
		usrDto.setId(user.getId());
		dtoList.add(usrDto);
		workspaceDto.setUsers(dtoList);
		workspaceService.insert(workspaceDto);
//		System.out.println(workspaceDto.getId());
		return ResponseEntity.ok(new MessageResponse("Insert Successfully!"));
	}

	@GetMapping(value = "/workspace/{userId}", produces = "application/json")
	public ResponseEntity<List<WorkspaceDto>> selectAllWorkspacec(@PathVariable Long userId) {
		List<WorkspaceDto> workspaces = workspaceService.getAllWorkspace(userId);
		return ResponseEntity.ok(workspaces);
	}

	@PutMapping(value = "/workspace/{workspaceId}", produces = "application/json")
	public ResponseEntity<Boolean> updateWorkspacec(@PathVariable Long workspaceId, @RequestBody Workspace workspace) {

		WorkspaceDto workspaceDto = new WorkspaceDto();
		workspaceDto.setId(workspaceId);
		workspaceDto.setDescription(workspace.getDescription());
		workspaceDto.setName(workspace.getName());
		workspaceService.updateWorkspace(workspaceDto);
		return ResponseEntity.ok(true);
	}

	@DeleteMapping(value = "/workspace/{workspaceId}", produces = "application/json")
	public ResponseEntity<Boolean> deleteWorkspacec(@PathVariable Long workspaceId) {
		
		workspaceService.deleteWorkspace(workspaceId);
		boardService.deleteBoardByWorkspaceId(workspaceId);
		List<BoardDto> boardList = boardService.genereateBoardByWorkspaceId(workspaceId);
		for (int i = 0; i < boardList.size(); i++) {
			taskService.deleteTaskByBoardId(boardList.get(i).getId());
		}
		return ResponseEntity.ok(true);
	}

}
