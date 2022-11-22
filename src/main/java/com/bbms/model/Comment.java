package com.bbms.model;

import java.sql.Date;
import java.time.LocalDate;

import com.bbms.dto.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	private Long id;
	private String content;
	private LocalDate createAt;
	//private Date updateAt;
	private String parentId;
	private Task task;
	private User user;
}
