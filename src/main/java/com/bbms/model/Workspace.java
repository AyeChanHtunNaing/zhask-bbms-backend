package com.bbms.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workspace {
	private Long id;
	private String name;
	private String description;
	private String createdBy;
	private boolean isMarked;
	private List<User> users;
	
}
