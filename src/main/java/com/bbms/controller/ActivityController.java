package com.bbms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bbms.dto.ActivityDto;
import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskDto;
import com.bbms.dto.TaskListDto;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Activity;
import com.bbms.model.Workspace;
import com.bbms.service.ActivityService;
import com.bbms.service.TaskListService;
import com.bbms.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskListService taskListService;
	
	@PostMapping(value = "/activity", produces = "application/json")
	public ResponseEntity<ActivityDto> createActivity(@RequestBody Activity activity) {

		ActivityDto activityDto = new ActivityDto();
		activityDto.setName(activity.getName());
		activityDto.setChecked(activity.isChecked());
		TaskDto taskDto = new TaskDto();
		taskDto.setId(activity.getTask().getId());
		activityDto.setTasks(taskDto);
		activityService.insert(activityDto);
		return ResponseEntity.ok(activityDto);

	}
	
	@GetMapping(value="/activity/{taskId}",produces="application/json")
	public ResponseEntity<List<ActivityDto>> showActivityByTaskId(@PathVariable Long taskId){
		List<ActivityDto> activityDto = activityService.selectActivityByTaskId(taskId);
		return ResponseEntity.ok(activityDto);
	}
	
	@PutMapping(value = "/activity/{activityId}", produces = "application/json")
	public ResponseEntity<Boolean> setActivity(@PathVariable Long activityId , @RequestBody Activity activity) {

		ActivityDto activityDto=new ActivityDto();
		activityDto.setId(activityId);
		activityDto.setName(activity.getName());
		activityDto.setChecked(activity.isChecked());	
	    activityService.updateActivity(activityDto);
		return ResponseEntity.ok(true);
	}
	
	@PutMapping(value = "/activity/taskList/{taskId}", produces = "application/json")
	public ResponseEntity<Boolean> setTaskList(@PathVariable Long taskId , @RequestBody Activity activity) {
	    List<String> check=new ArrayList<String>();
	    System.out.println("Hi"+taskId);
		List<ActivityDto> activityDto = activityService.selectActivityByTaskId(taskId);
		Long doing=activityService.getDoingId(taskId);
		Long done=activityService.getDoneId(taskId);
		TaskDto dto=taskService.getTaskbyId(taskId);
		TaskListDto doingDto=taskListService.getTaskListById(doing);
		TaskListDto doneDto=taskListService.getTaskListById(done);
		System.out.println(doing);
		System.out.println(done);
		for(ActivityDto activities:activityDto) {
			
			if(activities.isChecked()==true) {
				System.out.println("reach there");
				check.add("check");
			}  
			    if( check.size()!=activityDto.size() && activity.isChecked() ) {
				System.out.println("reach there chan....");
				ActivityDto act=new ActivityDto();
				act.setId(activity.getId());
				act.setName(activity.getName());
				act.setChecked(activity.isChecked());	
				/*task update */
				System.out.println(taskId);
				TaskDto taskDto = new TaskDto();
				taskDto.setId(taskId);
				taskDto.setContent(dto.getContent());
				taskDto.setDescription(dto.getDescription());
				taskDto.setCreateAt(LocalDate.now());
				taskDto.setUpdateAt(LocalDate.now());
				taskDto.setStartDate(dto.getStartDate());
				taskDto.setEndDate(dto.getEndDate());
				taskDto.setCreatedBy(dto.getCreatedBy());
				BoardDto boardDto = new BoardDto();
				boardDto.setId(dto.getBoard().getId());
				TaskListDto tasklistDto = new TaskListDto();
				tasklistDto.setId(doingDto.getId());
				taskDto.setTaskList(tasklistDto);
				taskDto.setBoard(boardDto);
				taskService.updateTaskList(taskDto);
				/*task update */
				TaskDto task = new TaskDto();
				task.setId(taskDto.getId());
				act.setTasks(taskDto);
				activityService.update(act);
			   
			} 
			 if(check.size()==activityDto.size()) {
				System.out.println("reach there pika ....");
				System.out.println(taskId);
				ActivityDto act=new ActivityDto();
				act.setId(activity.getId());
				act.setName(activity.getName());
				act.setChecked(activity.isChecked());	
				/*task update */
				TaskDto taskDto = new TaskDto();
				taskDto.setId(taskId);
				taskDto.setContent(dto.getContent());
				taskDto.setDescription(dto.getDescription());
				taskDto.setCreateAt(LocalDate.now());
				taskDto.setUpdateAt(LocalDate.now());
				taskDto.setStartDate(dto.getStartDate());
				taskDto.setEndDate(dto.getEndDate());
				taskDto.setCreatedBy(dto.getCreatedBy());
				BoardDto boardDto = new BoardDto();
				boardDto.setId(dto.getBoard().getId());
				TaskListDto tasklistDto = new TaskListDto();
				tasklistDto.setId(doneDto.getId());
				taskDto.setTaskList(tasklistDto);
				taskDto.setBoard(boardDto);
				taskService.updateTaskList(taskDto);
				/*task update */
				TaskDto task = new TaskDto();
				task.setId(taskDto.getId());
				act.setTasks(taskDto);
				activityService.update(act);
			} 
			
		}
		
		return ResponseEntity.ok(true);
	}


}
