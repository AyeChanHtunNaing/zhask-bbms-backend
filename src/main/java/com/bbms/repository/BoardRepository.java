package com.bbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bbms.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	@Query(value="SELECT `id` FROM board ORDER BY `id` DESC limit 1 ",nativeQuery=true)
	public Long takeLastId();
}
