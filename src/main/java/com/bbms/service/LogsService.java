package com.bbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbms.dto.LogsDto;
import com.bbms.repository.LogsRepository;

@Service
public class LogsService {
	@Autowired
	private LogsRepository logsRepository;
	
	public List<LogsDto> selectLogsByTaskId(Long taskId) {
		
		return logsRepository.getLogsByTask(taskId);
	}
	public void insert(LogsDto logsDto) {
	    logsRepository.save(logsDto);
		
	}
}
