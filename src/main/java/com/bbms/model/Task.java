package com.bbms.model;

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
	private TaskList taskList;
	private Board board;
	private List <User> users;
	
}
