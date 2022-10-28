package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.TaskDto;

public interface TaskRepository extends JpaRepository<TaskDto, Long> {
	
	@Query(value="SELECT * FROM task WHERE task_list_id=? ",nativeQuery=true)
	public List<TaskDto> selectAllTaskListId(Long taskId);
	
	@Query(value="SELECT task_list_id FROM task WHERE id=? ",nativeQuery=true)
	public Long selectTaskListId(Long taskId);

}
