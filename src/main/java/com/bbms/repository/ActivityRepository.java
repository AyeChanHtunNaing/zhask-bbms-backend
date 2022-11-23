package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbms.dto.ActivityDto;

@Transactional
@Repository
public interface ActivityRepository extends JpaRepository<ActivityDto, Long> {

	@Query(value = "SELECT * FROM activity WHERE tasks_id=? ", nativeQuery = true)
	public List<ActivityDto> getActivityByTask(Long taskId);

	@Modifying
	@Query(value = "UPDATE activity SET is_checked=?1 WHERE id=?2 ", nativeQuery = true)
	public void updateActivity(Boolean isChecked, Long activityId);

	@Query(value = "SELECT distinct taskList.id from taskList join board on taskList.board_id=board.id "
			+ "join task where task.board_id=board.id and taskList.title='Doing' and task.id=?", nativeQuery = true)
	public Long getDoingTaskListId(Long taskId);

	@Query(value = "SELECT distinct taskList.id from taskList join board on taskList.board_id=board.id "
			+ "join task where task.board_id=board.id and taskList.title='Done' and task.id=?", nativeQuery = true)
	public Long getDoneTaskListId(Long taskId);
}
