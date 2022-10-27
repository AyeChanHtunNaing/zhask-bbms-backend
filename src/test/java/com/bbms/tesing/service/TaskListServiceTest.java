package com.bbms.tesing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskListDto;
import com.bbms.repository.TaskListRepository;
import com.bbms.service.TaskListService;

@SpringBootTest
public class TaskListServiceTest {
	@Mock
	private TaskListRepository taskListRepository;
	@InjectMocks
	private TaskListService tasklistService;
	@Test
	public void saveTaskListTest() {
		BoardDto board=new BoardDto();
		board.setId((long) 1);
		
		TaskListDto taskList1 = new TaskListDto();
		taskList1.setTitle("ToDo");
		taskList1.setBoard(board);	
		taskList1.setBoard(board);
		tasklistService.insert(taskList1);
	
	}
	
	
	@Test
	public void showAllTaskListWithBoardIdIdTest() {
		BoardDto board=new BoardDto();
		board.setId((long) 2);
		
		TaskListDto taskList1 = new TaskListDto();
		taskList1.setTitle("ToDo");
		taskList1.setBoard(board);
		TaskListDto taskList2 = new TaskListDto();
		taskList2.setTitle("Doing");
		taskList2.setBoard(board);
		TaskListDto taskList3 = new TaskListDto();
		taskList3.setTitle("Done");
		taskList3.setBoard(board);
		
		List<TaskListDto> lists = new ArrayList<>();
		lists.add(taskList1);
		lists.add(taskList2);
		lists.add(taskList3);
		when(taskListRepository.getTaskListDtoList((long) 2)).thenReturn(lists);
		List<TaskListDto> taskList=tasklistService.getTaskListRelatedBoardId((long) 2);
		assertEquals(3,taskList.size());
	}

}
