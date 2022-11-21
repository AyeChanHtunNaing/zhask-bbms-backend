package com.bbms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbms.dto.ActivityDto;
import com.bbms.repository.ActivityRepository;
@Transactional
@Service
public class ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	public void insert(ActivityDto dto) {

		activityRepository.save(dto);
	}
	
	public List<ActivityDto> selectActivityByTaskId(Long taskId) {
		
		return activityRepository.getActivityByTask(taskId);
	}
	public void updateActivity(ActivityDto dto) {
		activityRepository.updateActivity(dto.isChecked(),dto.getId());
	}
	
	public Long getDoingId(Long taskId) {
		System.out.println("doing taskId is "+taskId);
		return activityRepository.getDoingTaskListId(taskId);
	}
	public Long getDoneId(Long taskId) {
		System.out.println("done taskId is "+taskId);
		return activityRepository.getDoneTaskListId(taskId);
	}
	public ActivityDto update(ActivityDto dto) {
		return activityRepository.save(dto);
		
	}
}
