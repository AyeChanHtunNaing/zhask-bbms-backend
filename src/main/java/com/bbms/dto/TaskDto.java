package com.bbms.dto;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bbms.model.Board;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Entity
@Table(name="task")
public class TaskDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="description")
	private String description;
	@Column(name="create_at")
	private LocalDate createAt;
	@Column(name="update_at")
	private LocalDate updateAt;
	@Column(name="delete_status", columnDefinition = "TINYINT  default 0", length = 1)
	private boolean deleteStatus;
	
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	private List <CommentDto> comments;
	
	@OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL)
	private List <ActivityDto> activities;
	
	
	@ManyToOne
	private BoardDto board;
	
	@OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL)
	private List <AttachmentDto> attachment;
	
	
	@ManyToOne
	private TaskListDto taskList;
	

	@ManyToOne
	private UserDto user;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public BoardDto getBoard() {
		return board;
	}

	public void setBoard(BoardDto board) {
		this.board = board;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public List<ActivityDto> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityDto> activities) {
		this.activities = activities;
	}

	public List<AttachmentDto> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<AttachmentDto> attachment) {
		this.attachment = attachment;
	}

	public TaskListDto getTaskList() {
		return taskList;
	}

	public void setTaskList(TaskListDto taskList) {
		this.taskList = taskList;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
