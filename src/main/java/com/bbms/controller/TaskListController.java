package com.bbms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.bbms.model.TaskList;
import com.bbms.service.TaskListService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
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

	@PutMapping(value = "/tasklist/{tasklistId}", produces = "application/json")
	public ResponseEntity<TaskListDto> updateTaskListTitle(@PathVariable Long tasklistId,
			@RequestBody TaskList taskList) {
		TaskListDto taskListDto = new TaskListDto();
		taskListDto.setId(tasklistId);
		taskListDto.setTitle(taskList.getTitle());
		taskListService.updateTaskListTitle(taskListDto);
		return ResponseEntity.ok(taskListDto);
	}

	@DeleteMapping(value = "/tasklist/{taskId}", produces = "application/json")
	public ResponseEntity<Boolean> deleteTaskList(@PathVariable Long taskId) {
		taskListService.deleteTaskList(taskId);
		return ResponseEntity.ok(true);
	}

	@GetMapping(value = "/tasklist/{userId}/{boardId}", produces = "application/json")
	public ResponseEntity<List<TaskListDto>> selectById(@PathVariable Long boardId, @PathVariable Long userId) {
		List<TaskListDto> tasklistmodel = taskListService.getTaskListRelatedBoardId(boardId, userId);
		return ResponseEntity.ok(tasklistmodel);
	}

	@GetMapping(value = "/tasklist/{taskListId}", produces = "application/json")
	public TaskListDto getTaskListById(@PathVariable Long taskListId) {
		return taskListService.getTaskListById(taskListId);
	}
}
