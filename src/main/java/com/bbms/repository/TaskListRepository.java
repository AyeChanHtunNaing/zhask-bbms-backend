package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.bbms.dto.TaskListDto;

@Transactional
public interface TaskListRepository extends JpaRepository<TaskListDto, Long> {

//	List<TaskListDto> findAllByIdAndDeleteStatus(Long boardId, boolean deleteStatus);
	@Query(value="SELECT * FROM tasklist WHERE board_id=? ",nativeQuery = true)
	public List<TaskListDto> getTaskListDtoList(Long boardId);
	
	@Modifying
	@Query(value="UPDATE tasklist SET title=? WHERE id=?",nativeQuery= true)
	public void updateTaskListTitle(String title,Long tasklistId);
	
}
