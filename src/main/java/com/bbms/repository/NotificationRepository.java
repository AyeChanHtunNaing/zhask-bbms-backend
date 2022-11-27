package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bbms.dto.NotificationDto;

@Transactional
public interface NotificationRepository extends JpaRepository<NotificationDto, Long> {
	@Query(value="SELECT * FROM notification WHERE user_id=? and status= 0 ",nativeQuery = true)
	public List<NotificationDto> getNotificationByUserId(Long userId);
	
	@Query(value="SELECT * FROM notification WHERE user_id=? ",nativeQuery = true)
	public List<NotificationDto> getAllNotificationByUserId(Long userId);

	@Modifying
	@Query(value="UPDATE notification SET status = 1 WHERE user_id = ? ",nativeQuery=true)
	public int trueNotiStatus(Long userId);
}