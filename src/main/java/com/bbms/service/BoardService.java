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
		
		boardRepository.save(dto);
		
	}

	public void insertTaskList() {
		
		// set board id as foreign key in tasklist table 
		
		Long boardId = boardRepository.takeLastId();
		BoardDto board=new BoardDto();
		board.setId(boardId);
		
		TaskListDto taskList = new TaskListDto();
		taskList.setTitle("ToDo");
		taskList.setBoard(board);
		TaskListDto taskList1 = new TaskListDto();
		taskList1.setTitle("Doing");
		taskList1.setBoard(board);
		TaskListDto taskList2 = new TaskListDto();
		taskList2.setTitle("Done");
		taskList2.setBoard(board);
		
		List<TaskListDto> lists = new ArrayList<>();
		lists.add(taskList);
		lists.add(taskList1);
		lists.add(taskList2);
		for(TaskListDto t : lists) {
			taskListRepository.save(t);
		}
				
	}
	
	public List<TaskList> showAllTaskList(Long boardId) {
		
		List<TaskListDto> taskLists = taskListRepository.findAllByIdAndDeleteStatus(boardId,false);
		TaskList taskList = new TaskList();
		List<TaskList> tasklistmodel=new ArrayList();
		for(TaskListDto list : taskLists) {			
			taskList.setId(list.getId());
			taskList.setTitle(list.getTitle());
			tasklistmodel.add(taskList);
		}	
		return  tasklistmodel;			
	}
}
