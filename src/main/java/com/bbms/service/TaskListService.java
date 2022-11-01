package com.bbms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bbms.dto.TaskListDto;
import com.bbms.repository.TaskListRepository;

@Service
public class TaskListService {

	@Autowired
	private TaskListRepository taskListRepository;

	public List<TaskListDto> getTaskListRelatedBoardId(Long boardId) {
		return taskListRepository.getTaskListDtoList(boardId);
	}

	public void insert(TaskListDto dto) {

		taskListRepository.save(dto);

	}
	public TaskListDto updateTaskList(TaskListDto dto) {
		
	  return taskListRepository.save(dto);	
	}
	
	public void deleteTaskList(Long taskId) {
		taskListRepository.deleteById(taskId);
	}
	
	public Optional<TaskListDto> getTaskList(Long tasklistId) {
		return taskListRepository.findById(tasklistId);
	}
}
