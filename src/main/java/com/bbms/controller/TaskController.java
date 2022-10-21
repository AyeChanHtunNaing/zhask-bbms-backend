package com.bbms.controller;

import java.time.LocalDate;
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

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskDto;
import com.bbms.dto.TaskListDto;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Board;
import com.bbms.model.Task;
import com.bbms.service.TaskService;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Controller
@Slf4j
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping(value = "/task", produces = "application/json")
	public ResponseEntity<TaskDto> createBoard(@RequestBody Task task) {

		TaskDto taskDto = new TaskDto();
		taskDto.setDescription(task.getDescription());
		taskDto.setCreateAt(LocalDate.now());
		TaskListDto tasklistDto = new TaskListDto();
		tasklistDto.setId(task.getTaskListDto().getId());
		taskService.insert(taskDto);

		return ResponseEntity.ok(taskDto);

	}
	
	@GetMapping(value = "/task/{tasklistId}", produces = "application/json")
	public ResponseEntity<List<TaskDto>> selectById(@PathVariable Long taskId) {

		List<TaskDto> taskmodel = taskService.getAllTaskId(taskId);
		return ResponseEntity.ok(taskmodel);
	}
	
}
