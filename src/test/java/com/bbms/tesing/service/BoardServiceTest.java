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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskListDto;
import com.bbms.repository.BoardRepository;
import com.bbms.repository.TaskListRepository;
import com.bbms.service.BoardService;
import com.bbms.service.TaskListService;

@SpringBootTest
public class BoardServiceTest {
	@Mock
	private BoardRepository boardRepository;
	@InjectMocks
	private BoardService boardService;
	@InjectMocks
	private TaskListService tasklistService;
	@Mock
	private TaskListRepository taskListRepository;
	 @Test
	public void getAllBoardWithWorkspaceIdTest() {
		   List<BoardDto> list=new ArrayList<BoardDto>();
		   BoardDto b1=new BoardDto();
		   b1.setId(1);
		   b1.setName("First Board");
		   b1.setDescription("Software Engineering");
		   BoardDto b2=new BoardDto();
		   b2.setId(2);
		   b2.setName("First Board");
		   b2.setDescription("Software Engineering");
		    list.add(b1);
			list.add(b2);
			when(boardRepository.getBoardDtoList(1)).thenReturn(list);
			List<BoardDto> boardList=boardService.getBoardRelatedWorkspace(1);
			assertEquals(2,boardList.size());
		    verify(boardRepository, times(1)).getBoardDtoList(1);
		}
	
	@Test
	public void saveBoardTest() {
		BoardDto b1=new BoardDto();
		   b1.setId(1);
		   b1.setName("First Board");
		   b1.setDescription("Software Engineering");
		   boardService.insert(b1);
			
		     verify(boardRepository,times(1)).save(b1);
	}
	
	@Test
	public void saveTaskListTest() {
		BoardDto board=new BoardDto();
		board.setId(1);
		
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
	  boardService.insertTaskList();
	
	}
//	@Test
//	public void showAllTaskListWithBoardIdIdTest() {
//		BoardDto board=new BoardDto();
//		board.setId(1);
//		
//		TaskListDto taskList1 = new TaskListDto();
//		taskList1.setTitle("ToDo");
//		taskList1.setBoard(board);
//		TaskListDto taskList2 = new TaskListDto();
//		taskList2.setTitle("Doing");
//		taskList2.setBoard(board);
//		TaskListDto taskList3 = new TaskListDto();
//		taskList3.setTitle("Done");
//		taskList3.setBoard(board);
//		
//		List<TaskListDto> lists = new ArrayList<>();
//		lists.add(taskList1);
//		lists.add(taskList2);
//		lists.add(taskList3);
//		when(taskListRepository.getB).thenReturn(lists);
//		List<TaskListDto> taskList=boardService.showAllTaskList(1);
//		assertEquals(3,taskList.size());
//	    verify(taskListRepository, times(1)).findAllByIdAndDeleteStatus(1, false);
//	}

}
