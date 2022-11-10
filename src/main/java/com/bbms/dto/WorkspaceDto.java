package com.bbms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name="workspace")
public class WorkspaceDto implements Serializable {

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
	@Column(name="delete_status", columnDefinition = "TINYINT  default 0", length = 1)
	private boolean deleteStatus;
	
	@ManyToMany
	@JoinTable(
			name="user_has_workspace",
			joinColumns = @JoinColumn(name="workspace_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List <UserDto> users;
	//@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    private List<BoardDto> boards = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(long i) {
		this.id = i;
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

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	public List<BoardDto> getBoards() {
		return boards;
	}

	public void setBoards(List<BoardDto> boards) {
		this.boards = boards;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
