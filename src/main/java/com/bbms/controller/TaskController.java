package com.bbms.controller;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bbms.dto.TaskDto;
import com.bbms.dto.TaskListDto;
import com.bbms.model.Task;
import com.bbms.service.TaskListService;
import com.bbms.service.TaskService;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskListService taskListService;

	@RequestMapping(value = "/task", produces = "application/json")
	public ResponseEntity<TaskDto> createTask(@RequestBody Task task) {

		TaskDto taskDto = new TaskDto();
		taskDto.setDescription(task.getDescription());
		taskDto.setCreateAt(LocalDate.now());
		taskDto.setUpdateAt(LocalDate.now());
		TaskListDto tasklistDto = new TaskListDto();
		tasklistDto.setId(task.getTaskList().getId());
		taskDto.setTaskList(tasklistDto);
		taskService.insert(taskDto);
		return ResponseEntity.ok(taskDto);

	}

	@GetMapping(value = "/task/{tasklistId}", produces = "application/json")
	public ResponseEntity<List<TaskDto>> selectById(@PathVariable Long tasklistId) {

		List<TaskDto> taskmodel = taskService.getAllTaskId(tasklistId);
		return ResponseEntity.ok(taskmodel);
	}
	@PutMapping(value = "/taskDescription/{taskId}", produces = "application/json")
	public ResponseEntity<TaskDto> update(@PathVariable Long taskId, @RequestBody Task task) {

		TaskDto taskDto = new TaskDto();
		taskDto.setId(taskId);
		taskDto.setDescription(task.getDescription());
		taskService.updateTaskDescription(taskDto);
		return ResponseEntity.ok(taskDto);

	}
	
	@PutMapping(value="/task/{taskId}",produces="application/json")
	public ResponseEntity<TaskDto> updateTaskDescription(@PathVariable Long taskId,@RequestBody Task task){
		
		TaskDto taskDto = new TaskDto();
		taskDto.setId(taskId);
		taskDto.setDescription(task.getDescription());
		taskDto.setCreateAt(LocalDate.now());
		taskDto.setUpdateAt(LocalDate.now());
		TaskListDto tasklistDto = new TaskListDto();
		tasklistDto.setId(task.getTaskList().getId());
		taskDto.setTaskList(tasklistDto);
		taskService.updateTask(taskDto);
		return ResponseEntity.ok(taskDto);
	}
	
	
	@DeleteMapping(value = "/task/{taskId}", produces = "application/json")
	public ResponseEntity<Boolean> deleteTaskList(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.ok(true);
	}

	@GetMapping(value = "/gettasklist/{taskId}", produces = "application/json")
	public Optional<TaskListDto> selectByTaskId(@PathVariable Long taskId) {

		Long tasklistId = taskService.getTaskListId(taskId);

		return taskListService.getTaskList(tasklistId);
	}

}
