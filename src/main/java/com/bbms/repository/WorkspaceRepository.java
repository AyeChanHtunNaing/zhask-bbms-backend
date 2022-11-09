package com.bbms.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.WorkspaceDto;

@Transactional
public interface WorkspaceRepository extends JpaRepository<WorkspaceDto, Long>{
	
	@Modifying
	@Query(value="UPDATE workspace SET name= ?1 WHERE id= ?2 ",nativeQuery=true)	
	public void updateWorkspace(String name , Long id);
	
	@Modifying
	@Query(value="UPDATE workspace SET delete_status=1 WHERE id=?",nativeQuery=true)
	public void deleteWorkspaceById(Long taskId);
	
}
