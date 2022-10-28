package com.bbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Workspace;
import com.bbms.repository.WorkspaceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WorkspaceService {
	@Autowired
	private WorkspaceRepository workspaceRepository;

	// create workspace
	public void insert(WorkspaceDto dto) {

		workspaceRepository.save(dto);

	}

	public List<WorkspaceDto> getAllWorkspace() {
		
		return workspaceRepository.findAll();
	}
	
	public WorkspaceDto updateWorkspace(WorkspaceDto dto) {
		return workspaceRepository.save(dto);
	}

	public void deleteWorkspace(WorkspaceDto dto) {
		
		workspaceRepository.delete(dto);
	}
	
}
