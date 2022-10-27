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

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskListDto;
import com.bbms.model.Board;
import com.bbms.model.TaskList;
import com.bbms.model.Workspace;
import com.bbms.repository.TaskListRepository;
import com.bbms.service.TaskListService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskListControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	  private ObjectMapper objectMapper;
	@MockBean
	private TaskListService tasklistService;
	@MockBean
	private TaskListRepository taskListRepository;
	@Test
	public void testcreatetasklistvalidate() throws Exception {
		  
		  
		  TaskList tasklist=new TaskList();
		  Board board=new Board();
		  board.setId((long) 1);
		  tasklist.setBoard(board);
		   mockMvc.perform(post("/api/v1/tasklist").contentType(org.springframework.http.MediaType.APPLICATION_JSON)
	               .content(objectMapper.writeValueAsString(tasklist)));
		          
    }
	
	@Test
	public void testgettasklistvalidate() throws Exception {
		 List<TaskListDto> list=new ArrayList<TaskListDto>();
		 TaskListDto t1=new TaskListDto();
		   t1.setId(1);
		   t1.setTitle("ToDo");
		   t1.setDeleteStatus(false);
		   TaskListDto t2=new TaskListDto();
		   t2.setId(1);
		   t2.setTitle("ToDo");
		   t2.setDeleteStatus(false);
		    list.add(t1);
			list.add(t2);
			 when(taskListRepository.getTaskListDtoList((long) 1)).thenReturn(list);
			    mockMvc.perform(get("/api/v1/tasklist/1"))
			        .andExpect(status().isOk());
	}
}
