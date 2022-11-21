package com.bbms.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="content")
	private String content;
	@Column(name="create_at")
	private Date createAt;
	@Column(name="update_at")
	private Date updateAt;
	
	@ManyToOne
	private TaskDto task;

	@ManyToMany
	@JoinTable(
			name="user_has_comment",
			joinColumns = @JoinColumn(name="comment_id"),
			inverseJoinColumns = @JoinColumn(name="user_id"),
			uniqueConstraints = { @UniqueConstraint(columnNames = { "comment_id", "user_id" }) }
			)
	private List <UserDto> users;

}
