package com.bbms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {
	
	private Long id;
	private String title;
	private String description;
	private boolean deleteStatus;
	private Board board;
	
}
