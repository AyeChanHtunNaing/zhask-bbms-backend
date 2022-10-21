package com.bbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbms.dto.TaskDto;
import com.bbms.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public void insert(TaskDto dto) {
		taskRepository.save(dto);
	}
	
	public List<TaskDto> getAllTaskId(Long tasklistId){
		 List<TaskDto> taskDtoList = taskRepository.selectAllTaskListId(tasklistId);
		 return taskDtoList;
	}
}
