package com.bbms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.AttachmentDto;
import com.bbms.dto.TaskDto;
import com.bbms.model.Attachment;
import com.bbms.service.AttachmentService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class AttachmentController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	@PostMapping(value = "/attachment", produces = "application/json")
	public ResponseEntity<AttachmentDto> createAttachment(@RequestBody Attachment attachment) {

		AttachmentDto attachmentDto = new AttachmentDto();
		attachmentDto.setFileUrl(attachment.getFileUrl());
		attachmentService.insert(attachmentDto);
		return ResponseEntity.ok(attachmentDto);

	}
	
	@GetMapping(value="/attachment/{activityId}",produces="application/json")
	public ResponseEntity<List<AttachmentDto>> showAttachmentByTaskId(@PathVariable Long activityId){
		
		List<AttachmentDto> attachmentDto = attachmentService.selectAttachmentByActivityId(activityId);
		return ResponseEntity.ok(attachmentDto);
	}
	
}
