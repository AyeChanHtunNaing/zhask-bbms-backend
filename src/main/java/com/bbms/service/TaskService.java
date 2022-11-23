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
	
	public TaskDto getTaskbyId(Long tasKId) {
		return taskRepository.selectTaskbyId(tasKId);
	}
	public TaskDto updateTask(TaskDto dto) {
		return taskRepository.save(dto);
	}
	
	public void updateTaskDescription(TaskDto dto) {
        System.out.println(dto.getDescription()); 
        System.out.println(dto.getId());
		taskRepository.updateTask(dto.getDescription(), dto.getId());
		 
	}
	
	public void deleteTask(Long taskId) {
		taskRepository.deleteTaskById(taskId);
	}
	
//	public Long getTaskListId(Long taskId){
//		Long tasklistid = taskRepository.selectTaskListId(taskId);
//		 return tasklistid;
//	}
	
	public List<TaskDto> showAllTaskByBoardId(Long taskId){
		return taskRepository.getAllTaskByBoardId(taskId);
	}
	
	public List<TaskDto> getAllTasks(){
		return taskRepository.getAllTasks();
	}
	
}
