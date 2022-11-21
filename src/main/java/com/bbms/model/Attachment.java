package com.bbms.model;


import com.bbms.dto.TaskDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
	
	private Long id;
	private String name;
	private String type;
    private byte[] data;
	private TaskDto task;
	
}
