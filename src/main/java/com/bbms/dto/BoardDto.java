package com.bbms.dto;

import java.io.Serializable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bbms.model.Workspace;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;
import lombok.Data;

@Entity
@Table(name="board")
public class BoardDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="createAt")
	private LocalDate createAt;

	
	@ManyToOne
	private UserDto user;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="board_task",
			joinColumns = @JoinColumn(name="task_id"),
			inverseJoinColumns = @JoinColumn(name="board_id")
			)
	private List <TaskDto> tasks;
	@JsonBackReference
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskListDto> taskLists=new ArrayList<>();
	@JsonManagedReference
	@ManyToOne
	private WorkspaceDto workspace;
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
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public List<TaskDto> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskDto> tasks) {
		this.tasks = tasks;
	}
	public List<TaskListDto> getTaskLists() {
		return taskLists;
	}
	public void setTaskLists(List<TaskListDto> taskLists) {
		this.taskLists = taskLists;
	}
	public WorkspaceDto getWorkspace() {
		return workspace;
	}
	public void setWorkspace(WorkspaceDto workspace) {
		this.workspace = workspace;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
