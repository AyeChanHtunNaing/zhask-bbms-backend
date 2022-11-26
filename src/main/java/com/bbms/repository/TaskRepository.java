package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.TaskDto;
import com.bbms.dto.WorkspaceDto;

@Transactional
public interface TaskRepository extends JpaRepository<TaskDto, Long> {

	@Query(value = "SELECT * FROM task WHERE task_list_id=? and delete_status=0 ", nativeQuery = true)
	public List<TaskDto> selectAllTaskListId(Long taskId);

	@Query(value = "SELECT task_list_id FROM task WHERE id=? and delete_status=0 ", nativeQuery = true)
	public Long selectTaskListId(Long taskId);

	@Query(value = "SELECT * FROM task WHERE id=? and delete_status=0 ", nativeQuery = true)
	public TaskDto selectTaskbyId(Long taskId);

	@Modifying
	@Query(value = "UPDATE task SET description= ?1 WHERE id= ?2 ", nativeQuery = true)
	public void updateTask(String description, Long id);

	@Query(value = "SELECT * FROM task WHERE board_id=? and delete_status=0 ", nativeQuery = true)
	public List<TaskDto> getAllTaskByBoardId(Long taskId);

	@Query(value = "SELECT * FROM task WHERE delete_status=0 ", nativeQuery = true)
	public List<TaskDto> getAllTasks();

	@Modifying
	@Query(value = "UPDATE task SET delete_status=1 WHERE id=?", nativeQuery = true)
	public void deleteTaskById(Long taskId);

	@Query(value = "SELECT * FROM task INNER JOIN user_has_task ON  task.id=user_has_task.task_id AND task_id=?1 AND user_id = ?2 AND delete_status=0 ", nativeQuery = true)
	public TaskDto checkTaskHasUser(Long taskId, Long userId);

	@Query(value = "SELECT  * FROM task INNER JOIN user_has_task ON  task.id=user_has_task.task_id AND user_id = ?1 AND delete_status=0 ", nativeQuery = true)
	public List<TaskDto> selectTaskByUserId(Long userId);
    
	// for report
	@Query(value = "SELECT * FROM task INNER JOIN tasklist ON task.task_list_id=tasklist.id INNER JOIN user_has_task ON task.id=user_has_task.task_id AND user_has_task.user_id=?1 AND task.delete_status=0 ", nativeQuery = true)
	public List<TaskDto> getTasksByid(Long userId);

	@Query(value = "SELECT * FROM task INNER JOIN tasklist ON task.task_list_id=tasklist.id INNER JOIN user_has_task ON task.id=user_has_task.task_id AND tasklist.title='DONE' AND user_has_task.user_id=?1 AND task.delete_status=0 ", nativeQuery = true)
	public List<TaskDto> getEndTasksByid(Long userId);

	@Query(value = "SELECT * FROM task INNER JOIN tasklist ON task.task_list_id=tasklist.id INNER JOIN user_has_task ON task.id=user_has_task.task_id  AND user_has_task.user_id=?1 AND task.delete_status=1", nativeQuery = true)
	public List<TaskDto> getCloseTasksByid(Long userId);

	@Modifying
	@Query(value = "UPDATE task SET task_list_id=?1 where id=?2", nativeQuery = true)
	public void updateTaskListbyTaskId(Long taskListId, Long taskId);

	@Modifying
	@Query(value = "UPDATE task SET delete_status=1 WHERE board_id=?1 ", nativeQuery = true)
	public void deleteTaskByBoardId(Long boardId);
}
