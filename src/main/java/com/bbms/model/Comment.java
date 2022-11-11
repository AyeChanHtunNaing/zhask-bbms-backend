package com.bbms.model;

import java.sql.Date;
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
	private Date createAt;
	private Date updateAt;
	private TaskDto task;
	
}
