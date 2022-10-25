package com.bbms.tesing.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bbms.model.InviteMember;
import com.bbms.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class InviteControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	  private ObjectMapper objectMapper;
	@Test
	public void testinvitemembervalidatetruetest() throws Exception {
		
		InviteMember member=new InviteMember();
		member.setEmail("hansunwayoo2k18@gmail.com,zinko2000htwe@gmail.com,zinkohtwe2000@gmail.com,kham.nyein.chan.aung@gmail.com");
         mockMvc.perform(post("/api/v1/invite")
        		 .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(member)));
               
	}
	
	@Test
	public void testinvitememberinvalidatetruetest() throws Exception {
		
		InviteMember member=new InviteMember();
		member.setEmail("hansunwayoo2k18");
         mockMvc.perform(post("/api/v1/invite")
        		 .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(member)));
               
	}
}
