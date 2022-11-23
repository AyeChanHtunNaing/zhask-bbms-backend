package com.bbms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.TaskDto;
import com.bbms.dto.UserDto;
import com.bbms.service.ReminderService;
import com.bbms.service.TaskService;

@RestController
public class testController {
	
	@Autowired
	private TaskService taskService;

	@GetMapping("/temptest")
//	@GetMapping(value = "/task/{tasklistId}", produces = "application/json")
	public ResponseEntity<List<TaskDto>> selectTasks() {
		List<TaskDto> taskmodel = taskService.getAllTasks();
		return ResponseEntity.ok(taskmodel);
	}

	
}
