package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bbms.dto.CommentDto;

public interface CommentRepository extends JpaRepository<CommentDto, Long>{

	@Query(value="SELECT * FROM activity WHERE task_Id=? ",nativeQuery = true)
	public List<CommentDto> getCommentByTask(Long taskId);
	
}
