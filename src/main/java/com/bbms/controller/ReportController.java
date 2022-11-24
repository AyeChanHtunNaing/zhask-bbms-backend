package com.bbms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bbms.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class ReportController {
	@Autowired
	private ReportService reportService;
	
	@GetMapping("report/workspace/{id}")
	public  void exportWorkspace(@PathVariable Long id) throws JRException, IOException{    
		reportService.generateWorkspace(id);
	}
	@GetMapping("report/board/{id}")
	public  void exportBoard(@PathVariable Long id) throws JRException, IOException{    
		reportService.generateBoard(id);
	}
	@GetMapping("report/assignedTask/{id}")
	public  void exportAssignedTasks(@PathVariable Long id) throws JRException, IOException{    
		reportService.generateAssignedTasks(id);
	}
	@GetMapping("report/endTask/{id}")
	public  void exportEndTasks(@PathVariable Long id) throws JRException, IOException{    
		reportService.generateEndTasks(id);
	}
	@GetMapping("report/closedTask/{id}")
	public  void exportClosedTasks(@PathVariable Long id) throws JRException, IOException{    
		reportService.generateClosedTasks(id);
	}
}
