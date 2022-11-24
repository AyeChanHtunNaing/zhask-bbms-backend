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

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="board")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@Column(name="is_marked", columnDefinition = "TINYINT  default 0", length = 1)
	private boolean isMarked;
	@Column(name="created_by")
	private String createdBy;
	
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
	@JsonIgnore
	private WorkspaceDto workspace;


	
}
