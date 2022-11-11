package com.bbms.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="tasklist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskListDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="title")
	private String title;
	@Column(name="delete_status" , columnDefinition = "TINYINT(1) default(0)")
	private boolean deleteStatus;
	
	@ManyToOne
	private BoardDto board;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL)
    private List<TaskDto> tasks = new ArrayList<>();

}
