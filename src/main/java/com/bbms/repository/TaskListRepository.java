package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskListDto;

public interface TaskListRepository extends JpaRepository<TaskListDto, Long> {

//	List<TaskListDto> findAllByIdAndDeleteStatus(Long boardId, boolean deleteStatus);
	@Query(value="SELECT * FROM tasklist WHERE board_Id=? ",nativeQuery = true)
	public List<TaskListDto> getTaskListDtoList(Long boardId);
}
