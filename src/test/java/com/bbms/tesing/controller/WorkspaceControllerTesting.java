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
		List<WorkspaceDto> list=new ArrayList<WorkspaceDto>();
		WorkspaceDto w1 = new WorkspaceDto();
		w1.setName("Aye Aye");
		w1.setDescription("Hi");
		WorkspaceDto w2 = new WorkspaceDto();
		w2.setName("Aye Aye");
		w2.setDescription("Hi");
        list.add(w1);
        list.add(w2);
         mockMvc.perform(post("/api/v1/workspace").contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(w1)));
               
	}
	
	@Test
	public void testgetworkspacevalidate() throws Exception {
		List<WorkspaceDto> list=new ArrayList<WorkspaceDto>();
		WorkspaceDto w1 = new WorkspaceDto();
		w1.setName("Aye Aye");
		w1.setDescription("Hi");
		WorkspaceDto w2 = new WorkspaceDto();
		w2.setName("Aye Aye");
		w2.setDescription("Hi");
        list.add(w1);
        list.add(w2);
		 when(workspaceRepository.findAll()).thenReturn(list);
		    mockMvc.perform(get("/api/v1/workspace"))
		        .andExpect(status().isOk());
		       
	}
	
	
}
