package com.bbms.model;

import com.bbms.dto.TaskDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noti {
private String email;
private String name;
private String content;
}
