package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.bbms.dto.CommentDto;

@Transactional
public interface CommentRepository extends JpaRepository<CommentDto, Long>{

	@Query(value="SELECT * FROM comment WHERE task_id=? and delete_status=0 ",nativeQuery = true)
	public List<CommentDto> getCommentByTask(Long taskId);
	
	@Modifying
	@Query(value="UPDATE comment SET content=?1 WHERE id=?2 ",nativeQuery=true)
	public void updateComment(String content, Long commentId);
	
	@Modifying
	@Query(value="UPDATE comment SET delete_status=1 WHERE id=? ",nativeQuery=true)
	public void deleteComment(Long commentId);
}
