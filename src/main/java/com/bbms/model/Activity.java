package com.bbms.model;

import java.sql.Date;
import java.util.List;

import com.bbms.dto.ActivityDto;
import com.bbms.dto.CommentDto;
import com.bbms.dto.TaskDto;
import com.bbms.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

	private Long id;
	private String name;
	private boolean isChecked;
	private Date dueDate;
	private Date startDate;
	private Date endDate;
	private TaskDto task;
	
}
