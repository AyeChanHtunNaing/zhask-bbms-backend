package com.bbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbms.dto.NotificationDto;
import com.bbms.dto.UserDto;
import com.bbms.repository.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	UserService uservice;

	@Autowired
	NotificationRepository notiRepo;

//	public NotificationDto preNoti(String invitor){
//		return notiRepo.save(NotificationDto.builder().status(invitor).build());
//	}

	public void createNoti(String CreatorEmail,String invitor, String member, String workspace){
		String content = member+" was added to "+workspace+" by "+invitor+" at "+new SimpleDateFormat("hh:mm(aa)-dd/MM/yyyy").format(new Date());
		notiRepo.save(NotificationDto.builder().content(content).status(false).user(uservice.getByEmail(CreatorEmail)).build());
	}

	public List<NotificationDto> getNotificationByUserId(Long userId){
		return notiRepo.getNotificationByUserId(userId);
	}
	
	public List<NotificationDto> getAllNotificationByUserId(Long userId){
		return notiRepo.getAllNotificationByUserId(userId);
	} 
	
	public int changeStatus(Long userId) {
		return notiRepo.trueNotiStatus(userId);
	}

}
