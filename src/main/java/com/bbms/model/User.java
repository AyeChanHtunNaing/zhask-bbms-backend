package com.bbms.model;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	

	private Long id;
	private String name;
	private String userName;
	private String email;
	private String password;
	private String token;
	//private LocalDate createdAt;
//	private LocalDate updateAt;
	private boolean status;
	private boolean deleteStatus;
	private String profile;
	private String pictureName;
	
}
