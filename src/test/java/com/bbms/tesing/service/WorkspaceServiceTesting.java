package com.bbms.tesing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Workspace;
import com.bbms.repository.WorkspaceRepository;
import com.bbms.service.WorkspaceService;
@SpringBootTest
public class WorkspaceServiceTesting {
	@Mock
	WorkspaceRepository workspaceRepository;
	@InjectMocks
	WorkspaceService workspaceService;
	@Test
	public void getAllWorkspaceTest() {
		   List<WorkspaceDto> list=new ArrayList<WorkspaceDto>();
		   WorkspaceDto w1=new WorkspaceDto();
		   w1.setId(1);
		   w1.setName("AA");
		   w1.setDescription("Hello");
		   WorkspaceDto w2=new WorkspaceDto();
		   w2.setId(2);
		   w2.setName("AA");
		   w2.setDescription("Hello");
		    list.add(w1);
			list.add(w2);
		
			when(workspaceRepository.findAll()).thenReturn(list);
			List<WorkspaceDto> userList=workspaceService.getAllWorkspace();
			assertEquals(2,userList.size());
			verify(workspaceRepository, times(1)).findAll();
		}
	@Test
	public void saveWorkspaceTest() {
		
		 WorkspaceDto w1=new WorkspaceDto();
		// w1.setId(1);
		 w1.setName("AA");
		 w1.setDescription("Hi");
		 workspaceService.insert(w1);
		
	     verify(workspaceRepository,times(1)).save(w1);
	     verifyNoMoreInteractions(workspaceRepository);
	}

}
