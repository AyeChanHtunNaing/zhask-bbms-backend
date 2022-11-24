package com.bbms.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskDto;
import com.bbms.dto.UserDto;
import com.bbms.dto.WorkspaceDto;

import lombok.Singular;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class ReportService {
	  
	
	@Autowired
	private WorkspaceService workspaceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private TaskService taskService;
	
	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/";
	
    public void generateWorkspace(Long id) throws JRException, IOException{
    	String path=DIRECTORY;
    	System.out.println(id);
    	UserDto user=userService.getById(id);
    	String name=user.getName();
    	String email=user.getEmail();
    	//List<WorkspaceDto> workspaces = workspaceService.getAllWorkspace(id);
    	List<WorkspaceDto> workspaces = workspaceService.getReportWorkspace(email);
    	System.out.println(workspaces.size());
	    File file=ResourceUtils.getFile("classpath:workspace.jrxml");
        JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(workspaces);
        Map<String,Object>  parameters=new HashMap<>();
        parameters.put("Email", email);
        JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parameters, dataSource);
        String filename=name+"-workspace-"+LocalDate.now();
        JasperExportManager.exportReportToPdfFile(jasperPrint, path+"/"+filename+".pdf");
    	
    }
    public void generateBoard(Long id) throws JRException, IOException{
    String path=DIRECTORY;
    UserDto user=userService.getById(id);
	String name=user.getName();
	String email=user.getEmail();
	List<BoardDto> boards=boardService.getBoardsByemail(email);
	System.out.println(boards.size());
	File file=ResourceUtils.getFile("classpath:board.jrxml");
    JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
    System.out.println("reach this");
    JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(boards);
    Map<String,Object>  parameters=new HashMap<>();
    parameters.put("Email", email);
    JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parameters, dataSource);
    String filename=name+"-board-"+LocalDate.now();
    JasperExportManager.exportReportToPdfFile(jasperPrint, path+"/"+filename+".pdf");
    }
    
    public void generateAssignedTasks(Long id) throws JRException, IOException{
    String path=DIRECTORY;
    UserDto user=userService.getById(id);
	String name=user.getName();
	String email=user.getEmail();
	List<TaskDto> tasks=taskService.getTasksbyId(id);
	System.out.println(tasks.size());
	File file=ResourceUtils.getFile("classpath:assigned_task.jrxml");
    JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
    System.out.println("reach this");
    JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(tasks);
    Map<String,Object>  parameters=new HashMap<>();
    parameters.put("userId", id);
    parameters.put("userName",name);
    JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parameters, dataSource);
    String filename=name+"-assignedTasks-"+LocalDate.now();
    JasperExportManager.exportReportToPdfFile(jasperPrint, path+"/"+filename+".pdf");
    }
    
    public void generateEndTasks(Long id) throws JRException, IOException{
        String path=DIRECTORY;
        UserDto user=userService.getById(id);
    	String name=user.getName();
    	String email=user.getEmail();
    	List<TaskDto> tasks=taskService.getEndTasksbyId(id);
    	File file=ResourceUtils.getFile("classpath:endTasks.jrxml");
        JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
        System.out.println("reach this");
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(tasks);
        Map<String,Object>  parameters=new HashMap<>();
        parameters.put("userId", id);
        parameters.put("userName",name);
        JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parameters, dataSource);
        String filename=name+"-endTasks-"+LocalDate.now();
        JasperExportManager.exportReportToPdfFile(jasperPrint, path+"/"+filename+".pdf");
        }
    public void generateClosedTasks(Long id) throws JRException, IOException{
        String path=DIRECTORY;
        UserDto user=userService.getById(id);
    	String name=user.getName();
    	String email=user.getEmail();
    	List<TaskDto> tasks=taskService.getClosedTasksbyId(id);
    	File file=ResourceUtils.getFile("classpath:closeTasks.jrxml");
        JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
        System.out.println("reach this");
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(tasks);
        Map<String,Object>  parameters=new HashMap<>();
        parameters.put("userId", id);
        parameters.put("userName",name);
        JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parameters, dataSource);
        String filename=name+"-closedTasks-"+LocalDate.now();
        JasperExportManager.exportReportToPdfFile(jasperPrint, path+"/"+filename+".pdf");
        }
}
