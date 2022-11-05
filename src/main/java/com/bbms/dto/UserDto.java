package com.bbms.dto;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user")
public class UserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="user_name")
	private String userName;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="token")
	private String token;
	@Column(name="create_at")
	private Date createAt;
	@Column(name="update_at")
	private Date updateAt;
	@Column(name="status")
	private boolean status;
	@Column(name="delete_status" , columnDefinition = "TINYINT  default 0", length = 1)
	private boolean deleteStatus;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BoardDto> boards = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ActivityDto> activity = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TaskDto> task = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommentDto> comment = new ArrayList<>();

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public List<BoardDto> getBoards() {
		return boards;
	}

	public void setBoards(List<BoardDto> boards) {
		this.boards = boards;
	}

	public List<TaskDto> getTask() {
		return task;
	}

	public void setTask(List<TaskDto> task) {
		this.task = task;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ActivityDto> getActivity() {
		return activity;
	}

	public void setActivity(List<ActivityDto> activity) {
		this.activity = activity;
	}

	public List<CommentDto> getComment() {
		return comment;
	}

	public void setComment(List<CommentDto> comment) {
		this.comment = comment;
	}
	
}
