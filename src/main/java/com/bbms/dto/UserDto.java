package com.bbms.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@Column(name="email",unique=true)
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="token")
	private String token;
	@Column(name="createAt")
	private LocalDate createAt;
	@Column(name="updateAt")
	private LocalDate updateAt;
	@Column(name="status")
	private boolean status;
	@Column(name="delete_status" , columnDefinition = "TINYINT  default 0", length = 1)
	private boolean deleteStatus;
	@Column(name="profile")
	@Lob
	private byte[] profile;
	@Column(name="pictureName")
	private String pictureName;
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<BoardDto> boards = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<ActivityDto> activity = new ArrayList<>();
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<TaskDto> task = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NotificationDto> notifications = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommentDto> comment = new ArrayList<>();
	
}
