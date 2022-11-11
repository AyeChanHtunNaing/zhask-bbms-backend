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
	private Workspace workSpace;
	private ArrayList<User> users;
	
}
