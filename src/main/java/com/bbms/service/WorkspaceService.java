package com.bbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbms.dto.BoardDto;
import com.bbms.dto.WorkspaceDto;

import com.bbms.repository.WorkspaceRepository;

@Service
public class WorkspaceService {

	@Autowired
	private WorkspaceRepository workspaceRepository;

	// create workspace
	public WorkspaceDto insert(WorkspaceDto dto) {

		return workspaceRepository.save(dto);

	}

	public List<WorkspaceDto> getAllWorkspace(Long userId) {

		return workspaceRepository.getWorkspaceByUserId(userId);
	}

	public List<WorkspaceDto> getReportWorkspace(String email) {

		return workspaceRepository.getWorkspacesByuserEmail(email);
	}

	public List<WorkspaceDto> getFavWorkspace(Long userId) {

		return workspaceRepository.getFavWorkspaceById(userId);
	}

	public void updateWorkspace(WorkspaceDto dto) {

		workspaceRepository.updateWorkspace(dto.getName(), dto.getId());
	}

	public void deleteWorkspace(Long workspaceId) {

		workspaceRepository.deleteWorkspaceById(workspaceId);
	}

	public WorkspaceDto getWorkspaceIdByWorkspace(Long workspaceId) {

		return workspaceRepository.selectWorkspaceIdByWorkspace(workspaceId);
	}

	public WorkspaceDto isExistWorkspace(Long workspaceId, Long userId) {
		return workspaceRepository.checkWorkspaceHasUser(workspaceId, userId);
	}

	public void setFavWorkspace(WorkspaceDto dto) {
		workspaceRepository.setFavWorkspace(dto.isMarked(), dto.getId());

	}

	public WorkspaceDto getWorkspaceByWorkspaceId(Long workspaceId) {

		return workspaceRepository.selectWorkspaceById(workspaceId);
	}

	public WorkspaceDto generateWorkspaceMemberByWorkspaceId(Long workspaceId) {
		return workspaceRepository.generateWorkspaceMemberByWorkspaceId(workspaceId);
	}
}
