package com.bbms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbms.dto.CommentDto;
import com.bbms.repository.CommentRepository;

public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public void insert(CommentDto dto) {

		commentRepository.save(dto);
	}
	
	public List<CommentDto> selectActivityByTaskId(Long taskId) {
		
		return commentRepository.getCommentByTask(taskId);
	}
}
