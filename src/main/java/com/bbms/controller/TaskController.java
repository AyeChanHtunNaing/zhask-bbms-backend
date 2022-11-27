package com.bbms.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskDto;
import com.bbms.dto.TaskListDto;
import com.bbms.dto.UserDto;
import com.bbms.model.Attachment;
import com.bbms.model.Noti;
import com.bbms.model.Task;
import com.bbms.model.User;
import com.bbms.repository.UserRepository;
import com.bbms.service.ActivityService;
import com.bbms.service.BoardService;
import com.bbms.service.EmailService;
import com.bbms.service.TaskListService;
import com.bbms.service.TaskService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private TaskListService taskListService;
	@PostMapping(value = "/task", produces = "application/json")
	public ResponseEntity<TaskDto> createTask(@RequestBody Task task) throws MessagingException {
      
		TaskDto taskDto = new TaskDto();
		taskDto.setDescription(task.getDescription());
		taskDto.setCreatedBy(task.getCreatedBy());
		taskDto.setCreateAt(LocalDate.now());
		taskDto.setUpdateAt(LocalDate.now());
		taskDto.setStartDate(task.getStartDate());
		taskDto.setEndDate(task.getEndDate());
		BoardDto boardDto = new BoardDto();
		boardDto.setId(task.getBoard().getId());
		TaskListDto tasklistDto = new TaskListDto();
		tasklistDto.setId(task.getTaskList().getId());
		taskDto.setBoard(boardDto);
		taskDto.setTaskList(tasklistDto);
		List<UserDto> dtoList = new ArrayList<UserDto>();
		UserDto usrDto = new UserDto();
		User user = task.getUsers().get(0);
		usrDto.setId(user.getId());
		dtoList.add(usrDto);
		taskDto.setUsers(dtoList);
		TaskDto returns=taskService.insert(taskDto);
		
		return ResponseEntity.ok(returns);

	}

	@GetMapping(value = "/task/{tasklistId}", produces = "application/json")
	public ResponseEntity<List<TaskDto>> selectById(@PathVariable Long tasklistId) {

		List<TaskDto> taskmodel = taskService.getAllTaskId(tasklistId);
		return ResponseEntity.ok(taskmodel);
	}

	@GetMapping(value = "/task/generateTaskByUserId/{userId}", produces = "application/json")
	public ResponseEntity<List<TaskDto>> generateTaskByUserId(@PathVariable Long userId) {
		List<TaskDto> dtoList = taskService.generateTaskByUserId(userId);
		return ResponseEntity.ok(dtoList);
	}

	@PutMapping(value = "/taskDescription/{taskId}", produces = "application/json")
	public ResponseEntity<TaskDto> update(@PathVariable Long taskId, @RequestBody Task task) {

		TaskDto taskDto = new TaskDto();
		taskDto.setId(taskId);
		taskDto.setDescription(task.getDescription());
		taskService.updateTaskDescription(taskDto);
		return ResponseEntity.ok(taskDto);

	}

	@PutMapping(value = "/task/edit/{taskId}", produces = "application/json")
	public ResponseEntity<TaskDto> updateTaskList(@PathVariable Long taskId, @RequestBody Task task) {

		TaskDto taskDto = new TaskDto();
		taskDto.setId(taskId);
		TaskListDto tasklistDto = new TaskListDto();
		tasklistDto.setId(task.getTaskList().getId());
		taskDto.setTaskList(tasklistDto);
		taskService.updateTaskList(taskDto);
		return ResponseEntity.ok(taskDto);

	}
	
	@PutMapping(value = "/task" ,produces="application/json")
	public ResponseEntity<TaskDto> updateTask(@RequestParam(value = "tasks",required = false) String tasks,
			@RequestParam(value = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper()
				   .registerModule(new ParameterNamesModule())
				   .registerModule(new Jdk8Module())
				   .registerModule(new JavaTimeModule());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	 
		Task task = mapper.readValue(tasks, Task.class);
		TaskDto taskDto = new TaskDto();
		taskDto.setId(task.getId());
		taskDto.setContent(task.getContent());
		taskDto.setDescription(task.getDescription());
		taskDto.setCreateAt(LocalDate.now());
		taskDto.setUpdateAt(LocalDate.now());
		taskDto.setStartDate(task.getStartDate());
		taskDto.setEndDate(task.getEndDate());
		taskDto.setCreatedBy(task.getCreatedBy());
		BoardDto boardDto = new BoardDto();
		boardDto.setId(task.getBoard().getId());
		TaskListDto tasklistDto = new TaskListDto();
		tasklistDto.setId(task.getTaskList().getId());
		taskDto.setTaskList(tasklistDto);
		taskDto.setBoard(boardDto);
		try {
			if(file!=null)
			{
			taskDto.setProfile(file.getBytes());
			taskDto.setPictureName(file.getOriginalFilename());
			}
			 System.out.println("File..........................................................."+file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("third" + e);
			e.printStackTrace();
		}
		
		List<UserDto> dtoList = new ArrayList<UserDto>();
		UserDto usrDto = new UserDto();
		System.out.println(task.getUsers().get(0));
		User user = task.getUsers().get(0);
		usrDto.setId(user.getId());
		dtoList.add(usrDto);
		taskDto.setUsers(dtoList);
		 System.out.println("File..........................................................."+file);
		taskService.updateTask(taskDto);
		return ResponseEntity.ok(taskDto);
	}

	@DeleteMapping(value = "/task/{taskId}", produces = "application/json")
	public ResponseEntity<Boolean> deleteTaskList(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.ok(true);
	}

//	Just for testing before we get UI please don't delete 
//	@GetMapping(value = "/gettasklist/{taskId}", produces = "application/json")
//	public Optional<TaskListDto> selectByTaskId(@PathVariable Long taskId) {
//
//		Long tasklistId = taskService.getTaskListId(taskId);
//
//		return taskListService.getTaskList(tasklistId);
//	}
	@PutMapping(value = "/task/editTaskList/{taskId}", produces = "application/json")
	public ResponseEntity<TaskDto> updateTaskListtoDone(@PathVariable Long taskId, @RequestBody Task task) {
		Long done=activityService.getDoneId(taskId);
		TaskListDto doneDto=taskListService.getTaskListById(done);
		TaskDto taskDto = new TaskDto();
		taskDto.setId(taskId);
		TaskListDto tasklistDto = new TaskListDto();
		tasklistDto.setId(doneDto.getId());
		taskDto.setTaskList(tasklistDto);
		taskService.updateTaskList(taskDto);
		return ResponseEntity.ok(taskDto);

	}

}
