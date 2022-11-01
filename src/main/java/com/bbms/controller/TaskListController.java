package com.bbms.controller;

import java.time.LocalDate;
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
import com.bbms.dto.TaskListDto;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Board;
import com.bbms.model.TaskList;
import com.bbms.service.BoardService;
import com.bbms.service.TaskListService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Controller
@Slf4j
public class TaskListController {

	@Autowired
	private TaskListService taskListService;

	@PostMapping(value = "/tasklist", produces = "application/json")
	public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskList taskList) {

		TaskListDto taskListDto = new TaskListDto();
		taskListDto.setTitle(taskList.getTitle());
		taskListDto.setDeleteStatus(false);
		BoardDto boardDto = new BoardDto();
		boardDto.setId(taskList.getBoard().getId());
		taskListDto.setBoard(boardDto);
		taskListService.insert(taskListDto);
		return ResponseEntity.ok(taskListDto);

	}

	@PutMapping(value = "/tasklist", produces = "application/json")
	public ResponseEntity<TaskListDto> updateTaskList(@RequestBody TaskList taskList) {

		TaskListDto taskListDto = new TaskListDto();
		taskListDto.setTitle(taskList.getTitle());
		taskListDto.setDeleteStatus(false);
		BoardDto boardDto = new BoardDto();
		boardDto.setId(taskList.getBoard().getId());
		taskListDto.setBoard(boardDto);
		TaskListDto taskListModel = taskListService.updateTaskList(taskListDto);
		return ResponseEntity.ok(taskListModel);
	}

	@DeleteMapping(value = "/taskList/{taskId}", produces = "application/json")
	public ResponseEntity<Boolean> deleteTaskList(@PathVariable Long taskId) {
		taskListService.deleteTaskList(taskId);
		return ResponseEntity.ok(true);
	}
	@GetMapping(value = "/tasklist/{boardId}", produces = "application/json")
	public ResponseEntity<List<TaskListDto>> selectById(@PathVariable Long boardId) {

		List<TaskListDto> tasklistmodel = taskListService.getTaskListRelatedBoardId(boardId);
		return ResponseEntity.ok(tasklistmodel);
	}
}
