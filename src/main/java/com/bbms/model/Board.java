package com.bbms.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Board {
	
	private Long id;
	private String name;
	private String description;
	private Workspace workSpace;
	
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
	public Workspace getWorkSpace() {
		return workSpace;
	}
	public void setWorkSpace(Workspace workSpace) {
		this.workSpace = workSpace;
	}
	
}
