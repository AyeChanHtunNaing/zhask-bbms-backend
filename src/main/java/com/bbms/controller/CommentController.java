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

import com.bbms.dto.CommentDto;
import com.bbms.dto.TaskDto;
import com.bbms.model.Comment;
import com.bbms.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Controller
@Slf4j
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping(value = "/comment", produces = "application/json")
	public ResponseEntity<CommentDto> createComment(@RequestBody Comment comment) {

		CommentDto commentDto = new CommentDto();
		commentDto.setContent(comment.getContent());
		commentDto.setCreateAt(comment.getCreateAt());
		commentDto.setUpdateAt(comment.getUpdateAt());
		TaskDto taskDto = new TaskDto();
		taskDto.setId(comment.getId());
		commentDto.setTask(taskDto);
		commentService.insert(commentDto);
		return ResponseEntity.ok(commentDto);

	}
	
	@GetMapping(value="/comment/{taskId}",produces="application/json")
	public ResponseEntity<List<CommentDto>> showCommentByTaskId(@PathVariable Long taskId){
		
		List<CommentDto> commentDto = commentService.selectActivityByTaskId(taskId);
		return ResponseEntity.ok(commentDto);
	}
	
}
