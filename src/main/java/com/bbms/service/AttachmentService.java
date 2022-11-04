package com.bbms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbms.dto.AttachmentDto;
import com.bbms.repository.AttachmentRepository;

@Service
public class AttachmentService {
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	public void insert(AttachmentDto dto) {

		attachmentRepository.save(dto);
	}
	
	public List<AttachmentDto> selectAttachmentByActivityId(Long activityId) {
		
		return attachmentRepository.getAttachmentByTask(activityId);
	}

}
