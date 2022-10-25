package com.bbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskListDto;
import com.bbms.repository.TaskListRepository;

@Service
public class TaskListService {

	@Autowired
	private TaskListRepository taskListRepository;

	public List<TaskListDto> getTaskListRelatedBoardId(long i) {
		return taskListRepository.getTaskListDtoList(i);
	}

	public void insert(TaskListDto dto) {

		taskListRepository.save(dto);

	}
}
