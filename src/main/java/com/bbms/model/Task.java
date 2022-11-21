package com.bbms.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.bbms.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	
	private Long id;
	private String description;
	private String content;
	private TaskList taskList;
	private String createdBy;
	private String taskListName;
	private Board board;
	private LocalDate startDate;
	private LocalDate endDate;
	private List <User> users;
	
}
