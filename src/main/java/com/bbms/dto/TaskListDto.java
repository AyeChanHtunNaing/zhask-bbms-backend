package com.bbms.dto;

import lombok.Data;

@Data
public class TaskListDto {
	
	private Long id;
	private String title;
	private String description;
	private boolean deleteStatus;
	
}
