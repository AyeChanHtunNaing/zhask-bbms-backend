package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbms.dto.TaskListDto;

public interface TaskListRepository extends JpaRepository<TaskListDto, Long> {

	List<TaskListDto> findAllByIdAndDeleteStatus(Long boardId, boolean deleteStatus);
	
}
