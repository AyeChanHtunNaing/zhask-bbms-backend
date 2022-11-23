package com.bbms.service;

import java.time.LocalDate;
import java.util.List;

import javax.mail.MessagingException;

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
	
	@Autowired
	private EmailService mailservice;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void checkTaskDate() throws MessagingException {
		List<TaskDto> taskmodel = service.getAllTasks();
		for(TaskDto temp : taskmodel) {
			LocalDate dateToRemind = temp.getStartDate().minusDays(1);
			if(LocalDate.now().isEqual(dateToRemind)) { 
				String mailBody = "Task:"+temp.getDescription()+" sa to ml blh lbh.";
				String subject = "Reminder";
				mailservice.sendEmailWithMimeMessage(temp.getCreatedBy(), mailBody, subject);
			}
		}
	}
}
