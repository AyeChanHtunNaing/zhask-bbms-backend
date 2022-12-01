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
	
	//sec min hour dayOfMonth(1-31) month dayOfWeek(0-6)(Sunday=0||7)
	@Scheduled(cron = "0 0 0 * * *")
	public void checkTaskDate() throws MessagingException {
		List<TaskDto> taskmodel = service.getAllTasks();
		for(TaskDto temp : taskmodel) {
			LocalDate startReminder = temp.getStartDate().minusDays(1);
			LocalDate endReminder = temp.getEndDate().minusDays(1);
			if(LocalDate.now().isEqual(startReminder)) { 
				String mailBody = "The Start Date of the Task:"+temp.getDescription()+" is "+temp.getStartDate()+".";
				String subject = "Reminder";
				mailservice.sendEmailWithMimeMessage(temp.getCreatedBy(), mailBody, subject);
			}
			if(LocalDate.now().isEqual(endReminder)) { 
				String mailBody = "The Due Date of the Task:"+temp.getDescription()+" is "+temp.getEndDate()+".";
				String subject = "Reminder";
				mailservice.sendEmailWithMimeMessage(temp.getCreatedBy(), mailBody, subject);
			}
		}
	}
}
