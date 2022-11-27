package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.NotificationDto;

public interface NotificationRepository extends JpaRepository<NotificationDto, Long> {
	@Query(value="SELECT * FROM notification WHERE user_id=? and status= 0 ",nativeQuery = true)
	public List<NotificationDto> getNotificationByUserId(Long userId);
}
