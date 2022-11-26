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

	public TaskDto insert(TaskDto dto) {
		return taskRepository.save(dto);
	}

	public List<TaskDto> getAllTaskId(Long tasklistId) {
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
		taskRepository.updateTask(dto.getDescription(), dto.getId());

	}
	public void updateTaskList(TaskDto dto) {
		taskRepository.updateTaskListbyTaskId(dto.getTaskList().getId(),dto.getId());

	}
	public void deleteTask(Long taskId) {
		taskRepository.deleteTaskById(taskId);
	}
	public void deleteTaskByBoardId(Long boardId) {
		taskRepository.deleteTaskByBoardId(boardId);
	}

	public List<TaskDto> showAllTaskByBoardId(Long taskId) {
		return taskRepository.getAllTaskByBoardId(taskId);
	}

	public TaskDto checkTaskHasUser(Long taskId, Long userId) {
		return taskRepository.checkTaskHasUser(taskId, userId);
	}

	public List<TaskDto> generateTaskByUserId(Long userId) {
		List<TaskDto> dtoList = taskRepository.selectTaskByUserId(userId);
		return dtoList;
	}

	public List<TaskDto> getAllTasks(){
		return taskRepository.getAllTasks();
	}
	//report
	public List<TaskDto> getTasksbyId(Long id ){
		return taskRepository.getTasksByid(id);
	}
	public List<TaskDto> getEndTasksbyId(Long id ){
		return taskRepository.getEndTasksByid(id);
	}
	public List<TaskDto> getClosedTasksbyId(Long id ){
		return taskRepository.getCloseTasksByid(id);
	}
}
