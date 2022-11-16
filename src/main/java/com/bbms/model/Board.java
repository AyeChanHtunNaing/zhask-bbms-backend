package com.bbms.model;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	
	private Long id;
	private String name;
	private String description;
	private String createdBy;
	private Workspace workSpace;
	private boolean isMarked;
	private ArrayList<User> users;
	
}
