package com.bbms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.bbms.dto.AttachmentDto;
import com.bbms.repository.AttachmentRepository;

public class AttachmentService {
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	public void insert(AttachmentDto dto) {

		attachmentRepository.save(dto);
	}
	
	public List<AttachmentDto> selectAttachmentByTaskId(Long taskId) {
		
		return attachmentRepository.getAttachmentByTask(taskId);
	}

}
