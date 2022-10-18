package com.bbms.model;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="activity")
public class Activity implements Serializable{

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
	@Column(name="attachment")
	private String attachment;
	@Column(name="due_date")
	private Date dueDate;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	
	@ManyToOne
	private Task tasks;
	
}
