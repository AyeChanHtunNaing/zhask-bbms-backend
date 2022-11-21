package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.TaskDto;

@Transactional
public interface TaskRepository extends JpaRepository<TaskDto, Long> {
	
	@Query(value="SELECT * FROM task WHERE task_list_id=? and delete_status=0 ",nativeQuery=true)
	public List<TaskDto> selectAllTaskListId(Long taskId);
	
	@Query(value="SELECT task_list_id FROM task WHERE id=? and delete_status=0 ",nativeQuery=true)
	public Long selectTaskListId(Long taskId);
	
	@Query(value="SELECT * FROM task WHERE id=? and delete_status=0 ",nativeQuery=true)
	public TaskDto selectTaskbyId(Long taskId);
	
	@Modifying
	@Query(value="UPDATE task SET description= ?1 WHERE id= ?2 ",nativeQuery=true)	
	public void updateTask(String description , Long id);
	
	@Query(value="SELECT * FROM task WHERE board_id=? and delete_status=0 ",nativeQuery=true)
	public List<TaskDto> getAllTaskByBoardId(Long taskId);
	
	@Modifying
	@Query(value="UPDATE task SET delete_status=1 WHERE id=?",nativeQuery=true)
	public void deleteTaskById(Long taskId);
}
