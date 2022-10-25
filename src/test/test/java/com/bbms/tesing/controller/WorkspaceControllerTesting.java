package com.bbms.tesing.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Workspace;
import com.bbms.repository.WorkspaceRepository;
import com.bbms.service.WorkspaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@AutoConfigureMockMvc
public class WorkspaceControllerTesting {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WorkspaceService workspaceService;
	@MockBean
	private WorkspaceRepository workspaceRepository;
	@Autowired
	  private ObjectMapper objectMapper;
	@Test
	public void testcreateworkspacevalidate() throws Exception {
		
		Workspace w1=new Workspace();
		   w1.setId(1);
		   w1.setName("First Workspace");
		   w1.setDescription("Hello User");
		  
         mockMvc.perform(post("/api/v1/workspace").contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(w1)));
               
	}
	
	@Test
	public void testgetworkspacevalidate() throws Exception {
		List<WorkspaceDto> list=new ArrayList<WorkspaceDto>();
		WorkspaceDto w1=new WorkspaceDto();
		   w1.setId(1);
		   w1.setName("First Workspace");
		   w1.setDescription("Hello User");
		   WorkspaceDto w2=new WorkspaceDto();
		   w2.setId(2);
		   w2.setName("Second Workspace");
		   w2.setDescription("Hello User");
        list.add(w1);
        list.add(w2);

		 when(workspaceRepository.findAll()).thenReturn(list);
		    mockMvc.perform(get("/api/v1/workspace"))
		        .andExpect(status().isOk());
		       
	}
	
	
}
