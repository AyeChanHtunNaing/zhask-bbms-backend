package com.bbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.NotificationDto;
import com.bbms.service.NotificationService;

@RestController
public class NotificationController {
	
	@Autowired
	private NotificationService notiService;

	@GetMapping(value = "/noti/{userId}", produces = "application/json")
	public ResponseEntity<List<NotificationDto>> getNotiByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(notiService.getNotificationByUserId(userId));
	}
	
	@GetMapping(value = "/noti/chg/{userId}", produces = "application/json")
	public ResponseEntity<Integer> changeNotiStatus(@PathVariable Long userId) {
		return ResponseEntity.ok(notiService.changeStatus(userId));
	}
	
	@GetMapping(value = "/allnoti/{userId}", produces = "application/json")
	public ResponseEntity<List<NotificationDto>> getAllNotiById(@PathVariable Long userId) {
		return ResponseEntity.ok(notiService.getAllNotificationByUserId(userId));
	}
	
}
