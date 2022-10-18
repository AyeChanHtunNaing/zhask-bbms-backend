package com.bbms.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskListDto;
import com.bbms.model.Board;
import com.bbms.model.TaskList;
import com.bbms.repository.BoardRepository;
import com.bbms.repository.TaskListRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private TaskListRepository taskListRepository;
	
	// create board 
	public void insert(BoardDto dto) {
		
		Board board = new Board();
		
		board.setName(dto.getName());
		boardRepository.save(board);
		
	}
	
	public void insertTaskList() {
		
		// set board id as foreign key in tasklist table 
		
		Long boardId = boardRepository.takeLastId();
		Board board=new Board();
		board.setId(boardId);
		
		TaskList taskList = new TaskList();
		taskList.setTitle("ToDo");
		taskList.setBoard(board);
		TaskList taskList1 = new TaskList();
		taskList1.setTitle("Doing");
		taskList1.setBoard(board);
		TaskList taskList2 = new TaskList();
		taskList2.setTitle("Done");
		taskList2.setBoard(board);
		
		List<TaskList> lists = new ArrayList<>();
		lists.add(taskList);
		lists.add(taskList1);
		lists.add(taskList2);
		for(TaskList t : lists) {
			taskListRepository.save(t);
		}
				
	}
	
	public List<TaskListDto> showAllTaskList(Long boardId) {
		
		List<TaskList> taskLists = taskListRepository.findAllByIdAndDeleteStatus(boardId,false);
		TaskListDto taskList = new TaskListDto();
		List<TaskListDto> dto=new ArrayList();
		for(TaskList list : taskLists) {			
			taskList.setId(list.getId());
			taskList.setTitle(list.getTitle());
			dto.add(taskList);
		}	
		return  dto;			
	}
}
