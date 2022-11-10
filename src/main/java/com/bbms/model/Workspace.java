package com.bbms.model;

import java.util.List;


public class Workspace {
	private Long id;
	private String name;
	private String description;
	private List<User> users;
	
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
	public Long getId() {
		return id;
	}
	public void setId(long i) {
		this.id = i;
	}
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
