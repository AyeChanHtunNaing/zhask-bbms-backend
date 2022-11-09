package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.BoardDto;

@Transactional
public interface BoardRepository extends JpaRepository<BoardDto, Long>{
	
	@Query(value="SELECT `id` FROM `board` WHERE delete_status=0 ORDER BY `id`  DESC limit 1 ",nativeQuery=true)
	public Long takeLastId();
	
	@Query(value="SELECT * FROM board WHERE workspace_Id=? and delete_status=0 ",nativeQuery = true)
	public List<BoardDto> getBoardDtoList(Long workspaceId);
	
	@Modifying
	@Query(value="UPDATE board SET name=? WHERE id=? ",nativeQuery=true)
	public void updateBoardById(String name, Long boardId);
	
	@Modifying
	@Query(value="UPDATE board SET delete_status=1 WHERE id=? ",nativeQuery=true)
	public void deleteBoardById(Long boardId);
	
}
