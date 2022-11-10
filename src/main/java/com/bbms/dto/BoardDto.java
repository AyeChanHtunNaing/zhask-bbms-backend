package com.bbms.dto;

import java.io.Serializable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	@Column(name="delete_status", columnDefinition = "TINYINT  default 0", length = 1)
	private boolean deleteStatus;

//	@ManyToOne
//	private UserDto user;
	
	@ManyToMany
	@JoinTable(
			name="user_has_board",
			joinColumns = @JoinColumn(name="board_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List <UserDto> users;
	
	//@JsonBackReference(value="tasks")
    @JsonIgnore
	@OneToMany(mappedBy = "board", cascade=CascadeType.ALL)
	private List <TaskDto> tasks;
    
    @JsonIgnore
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<TaskListDto> taskLists=new ArrayList<>();
	
	//@JsonBackReference
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
	
	public List<UserDto> getUsers() {
		return users;
	}
	public void setUsers(List<UserDto> users) {
		this.users = users;
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
	public boolean isDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public List<TaskDto> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskDto> tasks) {
		this.tasks = tasks;
	}
	
	
}
