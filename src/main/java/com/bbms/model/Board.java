package com.bbms.model;

import java.io.Serializable;

import java.sql.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
@Table(name="board")
public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="create_at")
	private Date createAt;
	@Column(name="update_at")
	private Date updateAt;

	@ManyToOne
	private User user;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="borad_task",
			joinColumns = @JoinColumn(name="task_id"),
			inverseJoinColumns = @JoinColumn(name="borad_id")
			)
	private List <Task> tasks;
	@OneToOne(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private TaskList taskLists;
	
}
