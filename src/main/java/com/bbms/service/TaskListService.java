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

	public List<TaskListDto> getTaskListRelatedBoardId(Long boardId, Long userId) {
		return taskListRepository.getTaskListDtoList(boardId,userId);
	}

	public void insert(TaskListDto dto) {

		taskListRepository.save(dto);

	}
	public void updateTaskList(TaskListDto dto) {
		
	  taskListRepository.save(dto);	
	}
	
	public void deleteTaskList(Long taskId) {
		taskListRepository.deleteTaskListById(taskId);
	}
	
	
	public void updateTaskListTitle(TaskListDto dto){
		taskListRepository.updateTaskListTitle(dto.getTitle(),dto.getId());
	}
   public TaskListDto getTaskListById(Long taskListId) {
	   return taskListRepository.getTaskListById(taskListId);
   }
}
