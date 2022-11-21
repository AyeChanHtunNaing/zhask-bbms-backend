package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.AttachmentDto;

public interface AttachmentRepository extends JpaRepository<AttachmentDto, Long> {
	
	@Query(value="SELECT * FROM attachment WHERE task_id=? ",nativeQuery = true)
	public List<AttachmentDto> getAttachmentByTask(Long taskId);
	
	@Query(value="SELECT * FROM attachment WHERE id=? ",nativeQuery = true)
	public AttachmentDto getAttachmentById(Long id);
}
