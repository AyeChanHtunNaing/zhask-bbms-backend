package com.bbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.LogsDto;
import com.bbms.dto.TaskDto;
import com.bbms.model.Logs;
import com.bbms.service.LogsService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class LogsController {
	@Autowired
	private LogsService logsService;
	
	@PostMapping(value = "/logs", produces = "application/json")
	public ResponseEntity<LogsDto> createLogs(@RequestBody Logs log) {

		LogsDto logsDto=new LogsDto();
		logsDto.setMessage(log.getMessage());
		TaskDto taskDto = new TaskDto();
		taskDto.setId(log.getTask().getId());
		System.out.println(taskDto.getId());
		logsDto.setTask(taskDto);
		logsService.insert(logsDto);
		return ResponseEntity.ok(logsDto);

	}
	
	@GetMapping(value="/logs/{taskId}",produces="application/json")
	public ResponseEntity<List<LogsDto>> showLogsByTaskId(@PathVariable Long taskId){
		List<LogsDto> logsDto = logsService.selectLogsByTaskId(taskId);
		return ResponseEntity.ok(logsDto);
	}
}
