package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbms.dto.WorkspaceDto;

@Transactional
@Repository
public interface WorkspaceRepository extends JpaRepository<WorkspaceDto, Long>{
	
	@Modifying
	@Query(value="UPDATE workspace SET name= ?1 WHERE id= ?2 ",nativeQuery=true)	
	public void updateWorkspace(String name , Long id);
	
	@Modifying
	@Query(value="UPDATE workspace SET delete_status=1 WHERE id=?",nativeQuery=true)
	public void deleteWorkspaceById(Long taskId);
	
	@Query (value = "SELECT DISTINCT * FROM workspace INNER JOIN user_has_workspace ON  workspace.id=user_has_workspace.workspace_id AND user_id = ?1 AND delete_status = 0 ", nativeQuery = true)
	public List<WorkspaceDto> getWorkspaceById(Long id);
    
	@Query (value = "SELECT workspace.*,user_has_workspace.user_id FROM workspace INNER JOIN user_has_workspace ON  workspace.id=user_has_workspace.workspace_id AND id = ?1 AND delete_status = 0 ", nativeQuery = true)
	public WorkspaceDto selectWorkspaceIdByWorkspace(Long workspaceId);
	
	
}
