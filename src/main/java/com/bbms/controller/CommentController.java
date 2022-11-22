package com.bbms.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.CommentDto;
import com.bbms.dto.TaskDto;
import com.bbms.dto.UserDto;
import com.bbms.model.Comment;
import com.bbms.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping(value = "/comment", produces = "application/json")
	public ResponseEntity<CommentDto> createComment(@RequestBody Comment comment) {

		CommentDto commentDto = new CommentDto();
		commentDto.setContent(comment.getContent());
		commentDto.setCreateAt(LocalDate.now());
		commentDto.setParentId(comment.getParentId());
		TaskDto taskDto = new TaskDto();
		taskDto.setId(comment.getTask().getId());
		UserDto userDto = new UserDto();
		userDto.setId(comment.getUser().getId());
		commentDto.setTask(taskDto);
		commentDto.setUser(userDto);
		commentService.insert(commentDto);
		return ResponseEntity.ok(commentDto);

	}

	@GetMapping(value = "/comment/{taskId}", produces = "application/json")
	public ResponseEntity<List<CommentDto>> showCommentByTaskId(@PathVariable Long taskId) {

		List<CommentDto> commentDto = commentService.selectCommentByTaskId(taskId);
		return ResponseEntity.ok(commentDto);
	}

	@PutMapping(value = "/comment", produces = "application/json")
	public ResponseEntity<Boolean> updateComment(@RequestBody Comment comment) {

		commentService.updateComment(comment.getContent() , comment.getId());
		return ResponseEntity.ok(true);
	}

	@DeleteMapping(value = "/comment/{commentId}", produces = "application/json")
	public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId) {

		commentService.deleteComment(commentId);
		return ResponseEntity.ok(true);
	}

}
