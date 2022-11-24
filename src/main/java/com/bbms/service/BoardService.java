package com.bbms.service;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bbms.dto.BoardDto;
import com.bbms.dto.TaskListDto;
import com.bbms.repository.BoardRepository;
import com.bbms.repository.TaskListRepository;

@Service
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

		// set board id as foreign key in task list table

		Long boardId = boardRepository.takeLastId();
		BoardDto board = new BoardDto();
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
		for (TaskListDto t : lists) {
			taskListRepository.save(t);
		}

	}

	public BoardDto isExitUserIdInBoard(Long userId) {

		return boardRepository.isExistUserIdInBoardId(userId);
	}

	public void updateBoard(BoardDto dto) {
		
		boardRepository.updateBoardById(dto.getName(), dto.getId());
	}

	public void deleteBoard(Long boardId) {
		boardRepository.deleteBoardById(boardId);
	}

	public List<BoardDto> getBoardRelatedWorkspace(Long workspaceId, Long userId) {

		return boardRepository.getBoardDtoList(workspaceId, userId);
	}

	/* fav start */
	public List<BoardDto> getFavBoardRelatedWorkspace(Long userId) {

		return boardRepository.getFavBoard(userId);
	}

	public void setFavBoard(BoardDto dto) {
		
		boardRepository.setFavBoard(dto.isMarked(), dto.getId());

	}

	/* fav end */
	public BoardDto getBoardByBoardId(Long boardId) {

		return boardRepository.selectBoardIdByBoard(boardId);
	}

	public BoardDto isExistBoard(Long boardId, Long userId) {

		return boardRepository.checkBoardByUser(boardId, userId);
	}

	public List<BoardDto> generateBoardListByUserId(Long userId) {

		return boardRepository.showBoardListByUserId(userId);
	}
	
    public List<BoardDto> getBoardsByemail(String email){
    	return boardRepository.showBoardsbyUserEmail(email);
    }
    
	public BoardDto generateBoardMemberByBoardId(Long boardId) {
		return boardRepository.generateBoardMemberByBoardId(boardId);
	}
	
	public void deleteBoardByWorkspaceId(Long workspaceId) {

		boardRepository.deleteBoardByWorkspaceId(workspaceId);
	}
	
	public List<BoardDto> genereateBoardByWorkspaceId(Long workspaceId){
		
		return boardRepository.showBoardsbyWorkspaceId(workspaceId);
	}
}