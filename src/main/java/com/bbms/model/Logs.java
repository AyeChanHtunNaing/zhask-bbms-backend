package com.bbms.model;

import com.bbms.dto.TaskDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logs {
  private Long id;
  private String message;
  private TaskDto task;
}
