package com.bbms.dto;

import java.io.Serializable;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="activity")
public class ActivityDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="is_checked", columnDefinition = "TINYINT  default 0", length = 1)
	private boolean isChecked;
	@Column(name="due_date")
	private Date dueDate;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	
	@ManyToOne
	private TaskDto tasks;
	
	@ManyToOne
	private UserDto user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	private List<AttachmentDto> attachement;
	
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

	public TaskDto getTasks() {
		return tasks;
	}

	public void setTasks(TaskDto tasks) {
		this.tasks = tasks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public List<AttachmentDto> getAttachement() {
		return attachement;
	}

	public void setAttachement(List<AttachmentDto> attachement) {
		this.attachement = attachement;
	}
	
}
