package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbms.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

	List<TaskList> findAllByIdAndDeleteStatus(Long boardId, boolean deleteStatus);
	
}
