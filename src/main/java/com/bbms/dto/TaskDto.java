package com.bbms.dto;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="task")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@Column(name="content")
	private String content;
	@Column(name="create_at")
	private LocalDate createAt;
	@Column(name="update_at")
	private LocalDate updateAt;
	@Column(name="delete_status", columnDefinition = "TINYINT  default 0", length = 1)
	private boolean deleteStatus;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="start_date")
	private LocalDate startDate;
	@Column(name="end_date")
	private LocalDate endDate;
	@Lob
	private byte[] profile;
	@Column(name="pictureName")
	private String pictureName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	private List <CommentDto> comments;
	
	@JsonIgnore
	@OneToMany(
		    mappedBy = "tasks", 
		    cascade = CascadeType.ALL)
	private List <ActivityDto> activities;
		
	@ManyToOne
	private BoardDto board;
	
	@ManyToOne
	private TaskListDto taskList;

//	@ManyToOne
//	private UserDto user;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name="user_has_task",
			joinColumns = @JoinColumn(name="task_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List <UserDto> users;
	
	@JsonIgnore
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	private List<AttachmentDto> attachement;
	
	@JsonIgnore
	@OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
	private List <LogsDto> logs;
}
