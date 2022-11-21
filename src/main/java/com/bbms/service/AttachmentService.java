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
	
	 public void store(MultipartFile file,AttachmentDto attachmentDto) throws IOException {
		    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		    AttachmentDto attachment=new AttachmentDto(fileName,file.getContentType(),file.getBytes());
            attachment.setId(attachmentDto.getId());
            attachment.setTask(attachmentDto.getTask());
		    attachmentRepository.save(attachment);
		  }
	public List<AttachmentDto> selectAttachmentByActivityId(Long taskId) {
		
		return attachmentRepository.getAttachmentByTask(taskId);
	}
	public void insert(AttachmentDto attachmentDto) {
	    attachmentRepository.save(attachmentDto);
		
	}

}
