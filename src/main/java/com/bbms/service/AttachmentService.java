package com.bbms.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bbms.dto.AttachmentDto;
import com.bbms.repository.AttachmentRepository;

@Service
public class AttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepository;
	

	public List<AttachmentDto> selectAttachmentByTaskId(Long taskId) {
	
		return attachmentRepository.getAttachmentByTask(taskId);
	}
	public void insert(AttachmentDto attachmentDto) {
	    attachmentRepository.save(attachmentDto);
		
	}
	public AttachmentDto getFile(Long id) {
		return attachmentRepository.getAttachmentById(id);
	}

}
