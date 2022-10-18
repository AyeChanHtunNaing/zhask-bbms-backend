package com.bbms.model;

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

import lombok.Data;


@Entity
@Data
@Table(name="task")
public class Task implements Serializable{
	
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
	private Date createAt;
	@Column(name="update_at")
	private Date updateAt;
	@Column(name="delete_status", columnDefinition = "TINYINT  default 0", length = 1)
	private boolean deleteStatus;
	
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
	private List <Comment> comments;
	@OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true)
	private List <Activity> activities;
	@OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true)
	private List <Attachment> attachment;
	@ManyToOne
	private TaskList taskList;
	@ManyToOne
	private User user;
}
