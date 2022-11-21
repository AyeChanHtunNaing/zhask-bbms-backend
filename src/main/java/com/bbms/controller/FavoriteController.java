package com.bbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.BoardDto;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Board;
import com.bbms.model.Workspace;
import com.bbms.service.BoardService;
import com.bbms.service.WorkspaceService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class FavoriteController {
	@Autowired
	private WorkspaceService workspaceService;

	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = "/favorite/workspace/{userId}", produces = "application/json")
	public ResponseEntity<List<WorkspaceDto>> selectFavoriteWorkspacec(@PathVariable Long userId) {
		List<WorkspaceDto> workspaces = workspaceService.getFavWorkspace(userId);
		return ResponseEntity.ok(workspaces);
	}
	@PutMapping(value = "/favorite/workspace/{workspaceId}", produces = "application/json")
	public ResponseEntity<Boolean> setFavWorkspacec(@PathVariable Long workspaceId , @RequestBody Workspace workspace) {

		WorkspaceDto workspaceDto = new WorkspaceDto();
		workspaceDto.setId(workspaceId);
		workspaceDto.setDescription(workspace.getDescription());
		workspaceDto.setName(workspace.getName());
		workspaceDto.setMarked(workspace.isMarked());
	    workspaceService.setFavWorkspace(workspaceDto);
		return ResponseEntity.ok(true);
	}
	@GetMapping(value = "/favorite/board/{userId}", produces = "application/json")
	public ResponseEntity<List<BoardDto>> selectFavoriteBoard(@PathVariable Long userId) {

		List<BoardDto> boardList = boardService.getFavBoardRelatedWorkspace(userId);
		return ResponseEntity.ok(boardList);
	}


	@PutMapping(value = "/favorite/board/{boardId}", produces = "application/json")
	public ResponseEntity<Boolean> setFavBoard(@PathVariable Long boardId ,@RequestBody Board board) {
        System.out.println(board.isMarked());
		BoardDto boardDto = new BoardDto();
		boardDto.setId(boardId);
		boardDto.setName(board.getName());
		boardDto.setMarked(board.isMarked());
		boardService.setFavBoard(boardDto);
		return ResponseEntity.ok(true);
	}

}
