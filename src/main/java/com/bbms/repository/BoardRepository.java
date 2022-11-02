package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.BoardDto;


public interface BoardRepository extends JpaRepository<BoardDto, Long>{
	
	@Query(value="SELECT `id` FROM `board` ORDER BY `id` DESC limit 1 ",nativeQuery=true)
	public Long takeLastId();
	
	@Query(value="SELECT * FROM board WHERE workspace_Id=? ",nativeQuery = true)
	public List<BoardDto> getBoardDtoList(Long workspaceId);
	
}
