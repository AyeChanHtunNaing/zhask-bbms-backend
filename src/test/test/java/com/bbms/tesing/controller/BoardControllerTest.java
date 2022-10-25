package com.bbms.tesing.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bbms.dto.BoardDto;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Board;
import com.bbms.model.Workspace;
import com.bbms.repository.BoardRepository;
import com.bbms.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BoardRepository boardRepository;
	@MockBean
	private BoardService boardService;
	@Autowired
	  private ObjectMapper objectMapper;
	@Test
	public void testcreateboardvalidate() throws Exception {
		  
		  
		  Board board=new Board();
		  Workspace workspace=new Workspace();
		  workspace.setId(1);
		  board.setWorkSpace(workspace);
		   mockMvc.perform(post("/api/v1/board").contentType(org.springframework.http.MediaType.APPLICATION_JSON)
	               .content(objectMapper.writeValueAsString(board)));
		          
    }
	
	@Test
	public void testgetboardvalidate() throws Exception {
		 List<BoardDto> list=new ArrayList<BoardDto>();
		   BoardDto b1=new BoardDto();
		   b1.setId(1);
		   b1.setName("First Board");
		   b1.setDescription("Software Engineering");
		   BoardDto b2=new BoardDto();
		   b2.setId(2);
		   b2.setName("First Board");
		   b2.setDescription("Software Engineering");
		    list.add(b1);
			list.add(b2);
			 when(boardRepository.getBoardDtoList(1)).thenReturn(list);
			    mockMvc.perform(get("/api/v1/board/1"))
			        .andExpect(status().isOk());
	}
	}

