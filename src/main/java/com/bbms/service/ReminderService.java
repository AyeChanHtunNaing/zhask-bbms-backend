package com.bbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bbms.dto.TaskDto;



@Service
@EnableScheduling
public class ReminderService {
	
	@Autowired
	private TaskService service;
	
	@Scheduled(cron = "0 30 11 * * *")
	public void checkTaskDate() {
		System.out.println("++++checkTaskDate++++");
		List<TaskDto> dto = service.getAllTasks();
		System.out.println("here");
		System.out.println(dto);
		System.out.println("++++checkTaskDate++++");
		for(TaskDto temp : dto ) {
			System.out.println(temp.toString());
		}
	}
}
