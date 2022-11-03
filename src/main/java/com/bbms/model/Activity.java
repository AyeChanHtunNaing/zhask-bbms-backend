package com.bbms.model;

import java.sql.Date;

import com.bbms.dto.TaskDto;

public class Activity {

	private Long id;
	private String name;
	private boolean isChecked;
	private Date dueDate;
	private Date startDate;
	private Date endDate;
	private TaskDto task;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public TaskDto getTask() {
		return task;
	}
	public void setTask(TaskDto task) {
		this.task = task;
	}
	
	
	
}
