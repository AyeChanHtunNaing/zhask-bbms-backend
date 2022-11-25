package com.bbms.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bbms.dto.AttachmentDto;
import com.bbms.dto.TaskDto;
import com.bbms.model.Attachment;
import com.bbms.service.AttachmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class AttachmentController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	@PostMapping(value = "/attachment/upload", produces = "application/json")
	public ResponseEntity<AttachmentDto> createAttachment(@RequestParam("file") MultipartFile file,@RequestParam("attachment") String attach) throws JsonMappingException,JsonProcessingException,IOException {
		ObjectMapper mapper = new ObjectMapper()
				   .registerModule(new ParameterNamesModule())
				   .registerModule(new Jdk8Module())
				   .registerModule(new JavaTimeModule());
		Attachment attachment=mapper.readValue(attach,Attachment.class);
		System.out.println("attach :"+attachment);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		AttachmentDto attachmentDto=new AttachmentDto();
		attachmentDto.setName(fileName);
		attachmentDto.setType(file.getContentType());
		attachmentDto.setData(file.getBytes());
		
		
		
		TaskDto taskDto = new TaskDto();
		taskDto.setId(attachment.getTask().getId());
		attachmentDto.setTask(taskDto);
		attachmentService.insert(attachmentDto);
		return ResponseEntity.ok(attachmentDto);
	}

	
	
	
	@GetMapping(value="/attachment/{taskId}",produces="application/json")
	public ResponseEntity<List<AttachmentDto>> showAttachmentByTaskId(@PathVariable Long taskId){
		
		List<AttachmentDto> attachmentDto = attachmentService.selectAttachmentByTaskId(taskId);
		return ResponseEntity.ok(attachmentDto);
	}
	 @GetMapping("/attachment/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
	   AttachmentDto attachment = attachmentService.getFile(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
	        .body(attachment.getData());
	  }
	
}
