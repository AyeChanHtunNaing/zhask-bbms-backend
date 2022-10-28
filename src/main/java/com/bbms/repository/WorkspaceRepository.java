package com.bbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbms.dto.WorkspaceDto;

public interface WorkspaceRepository extends JpaRepository<WorkspaceDto, Long>{
	

}
