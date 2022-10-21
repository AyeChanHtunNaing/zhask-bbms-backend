package com.bbms.model;


import com.bbms.dto.TaskListDto;

public class Task {
	
	private Long id;
	private String description;
	private TaskListDto taskListDto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TaskListDto getTaskListDto() {
		return taskListDto;
	}
	public void setTaskListDto(TaskListDto taskListDto) {
		this.taskListDto = taskListDto;
	}
}
