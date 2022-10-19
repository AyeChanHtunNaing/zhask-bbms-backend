package com.bbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskListDto;
import com.bbms.model.Board;
import com.bbms.model.TaskList;
import com.bbms.model.Workspace;
import com.bbms.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Controller
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;

	@PostMapping(value = "/board", produces = "application/json")
	public ResponseEntity<BoardDto> createBoard(@RequestBody Board board) {
		
        BoardDto boarddto=new BoardDto();
        boarddto.setName(board.getName());
		boardService.insert(boarddto);
		boardService.insertTaskList();
		return ResponseEntity.ok(boarddto);

	}
	
	@GetMapping(value="/board/{boardId}",produces="application/json")
	public ResponseEntity<List<TaskList>> selectAll(@PathVariable Long boardId) {
		
		List<TaskList> tasklistmodel= boardService.showAllTaskList(boardId);		
		return ResponseEntity.ok(tasklistmodel);
	}
	
	
}
