package com.bbms.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskDto;
import com.bbms.dto.TaskListDto;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Board;
import com.bbms.model.TaskList;
import com.bbms.model.Workspace;
import com.bbms.service.BoardService;
import com.bbms.service.TaskService;
import com.bbms.service.WorkspaceService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Controller
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private TaskService taskService;

	@PostMapping(value = "/board", produces = "application/json")
	public ResponseEntity<BoardDto> createBoard(@RequestBody Board board) {

		BoardDto boardDto = new BoardDto();
		boardDto.setName(board.getName());
		boardDto.setDescription(board.getDescription());
		boardDto.setCreateAt(LocalDate.now());
		WorkspaceDto workDto = new WorkspaceDto();
		workDto.setId(board.getWorkSpace().getId());
		boardDto.setWorkspace(workDto);
		boardService.insert(boardDto);
		boardService.insertTaskList();
		return ResponseEntity.ok(boardDto);

	}

	@PutMapping(value = "/board/{boardId}", produces = "application/json")
	public ResponseEntity<Boolean> updateBoard(@PathVariable Long boardId ,@RequestBody Board board) {

		BoardDto boardDto = new BoardDto();
		boardDto.setId(boardId);
		boardDto.setName(board.getName());
		boardService.updateBoard(boardDto);
		return ResponseEntity.ok(true);
	}

	@DeleteMapping(value = "/board/{boardId}", produces = "application/json")
	public ResponseEntity<Boolean> deleteBaord(@PathVariable Long boardId) {
		boardService.deleteBoard(boardId);
		return ResponseEntity.ok(true);
	}

	@GetMapping(value = "/board/{workspaceId}", produces = "application/json")
	public ResponseEntity<List<BoardDto>> selectAllBoard(@PathVariable Long workspaceId) {

		List<BoardDto> boardList = boardService.getBoardRelatedWorkspace(workspaceId);
		return ResponseEntity.ok(boardList);
	}
	
	@GetMapping(value="/board/showalltaskbyboard/{boardId}",produces="application/json")
	public ResponseEntity<List<TaskDto>> showAllTaskByBoardId(@PathVariable Long boardId){
		
		List<TaskDto> taskDto = taskService.showAllTaskByBoardId(boardId);
		return ResponseEntity.ok(taskDto);
	}
	
}
