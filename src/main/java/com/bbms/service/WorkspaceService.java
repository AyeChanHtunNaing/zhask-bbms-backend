package com.bbms.service;

import java.util.ArrayList;
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
//    	List<Workspace> workspaces=workspaceRepository.findAll();
//    	WorkspaceDto workspacelist = new WorkspaceDto();
//   		List<WorkspaceDto> dto=new ArrayList();
//   		for(Workspace workspace : workspaces) {			
//   			workspacelist .setId( workspace.getId());
//   			workspacelist .setName( workspace.getName());
//   			workspacelist .setDescription( workspace.getDescription());
//   			dto.add(workspacelist);
//   		}	
//   		return  dto;
    	   return workspaceRepository.findAll();
		}

}
