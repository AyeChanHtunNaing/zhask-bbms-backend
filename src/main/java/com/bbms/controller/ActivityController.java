package com.bbms.controller;

import java.util.List;
import java.util.Optional;

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
import com.bbms.dto.ActivityDto;
import com.bbms.dto.TaskDto;
import com.bbms.model.Activity;
import com.bbms.service.ActivityService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@PostMapping(value = "/activity", produces = "application/json")
	public ResponseEntity<ActivityDto> createActivity(@RequestBody Activity activity) {

		ActivityDto activityDto = new ActivityDto();
		activityDto.setName(activity.getName());
		activityDto.setDueDate(activity.getDueDate());
		activityDto.setStartDate(activity.getStartDate());
		activityDto.setEndDate(activity.getEndDate());
		activityDto.setChecked(activity.isChecked());
		TaskDto taskDto = new TaskDto();
		taskDto.setId(activity.getId());
		activityDto.setTasks(taskDto);
		activityService.insert(activityDto);
		return ResponseEntity.ok(activityDto);

	}
	
	@GetMapping(value="/activity/{taskId}",produces="application/json")
	public ResponseEntity<List<ActivityDto>> showActivityByTaskId(@PathVariable Long taskId){
		
		List<ActivityDto> activityDto = activityService.selectActivityByTaskId(taskId);
		return ResponseEntity.ok(activityDto);
	}
	
}
