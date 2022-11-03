package com.bbms.model;

import java.sql.Date;

import com.bbms.dto.TaskDto;

public class Comment {

	private Long id;
	private String content;
	private Date createAt;
	private Date updateAt;
	private TaskDto task;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public TaskDto getTask() {
		return task;
	}
	public void setTask(TaskDto task) {
		this.task = task;
	}
	
}
