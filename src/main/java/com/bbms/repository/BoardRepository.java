package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbms.dto.BoardDto;
import com.bbms.dto.WorkspaceDto;

@Transactional
@Repository
public interface BoardRepository extends JpaRepository<BoardDto, Long> {

	@Query(value = "SELECT `id` FROM `board` WHERE delete_status=0 ORDER BY `id`  DESC limit 1 ", nativeQuery = true)
	public Long takeLastId();

	@Query(value = "SELECT DISTINCT * FROM board INNER JOIN user_has_board ON workspace_id=?1 AND board.id=user_has_board.board_id AND delete_status=0 AND user_id=?2 ", nativeQuery = true)
	public List<BoardDto> getBoardDtoList(Long workspaceId, Long userId);

	@Query(value = "SELECT DISTINCT * FROM board INNER JOIN user_has_board ON  board.id=user_has_board.board_id AND delete_status=0 AND user_id=?1 AND is_marked=1", nativeQuery = true)
	public List<BoardDto> getFavBoard(Long userId);

	@Modifying
	@Query(value = "UPDATE board SET name=? WHERE id=? ", nativeQuery = true)
	public void updateBoardById(String name, Long boardId);

	@Modifying
	@Query(value = "UPDATE board SET is_marked= ?1 WHERE id= ?2 ", nativeQuery = true)
	public void setFavBoard(Boolean checked, Long boardId);

	@Modifying
	@Query(value = "UPDATE board SET delete_status=1 WHERE id=? ", nativeQuery = true)
	public void deleteBoardById(Long boardId);

	@Query(value = "SELECT board.*,user_has_board.user_id FROM board INNER JOIN user_has_board ON  board.id=user_has_board.board_id AND id = ?1 AND delete_status = 0 ", nativeQuery = true)
	public BoardDto selectBoardIdByBoard(Long boardId);

	@Query(value = "SELECT * FROM board INNER JOIN user_has_board ON board.id=user_has_board.board_id AND board_id=?1 AND user_id=?2 AND board.delete_status=0 ", nativeQuery = true)
	public BoardDto checkBoardByUser(Long boardId, Long userId);

	@Query(value = "SELECT DISTINCT * FROM board INNER JOIN user_has_board ON  board.id=user_has_board.board_id  AND user_id=?1  AND delete_status=0 ", nativeQuery = true)
	public List<BoardDto> showBoardListByUserId(Long userId);

	@Query(value = "SELECT * FROM board INNER JOIN user_has_board ON  board.id=user_has_board.board_id  AND id=?1  AND delete_status=0 ", nativeQuery = true)
	public BoardDto generateBoardMemberByBoardId(Long boardId);

	@Query(value = "SELECT * FROM board INNER JOIN user_has_board ON  board.id=user_has_board.board_id  AND user_id=?1  AND delete_status=0 ", nativeQuery = true)
	public BoardDto isExistUserIdInBoardId(Long userId);

	@Query(value = "SELECT * FROM board INNER JOIN user ON board.created_by=user.email AND user.email=?1 AND board.delete_status=0 ", nativeQuery = true)
	public List<BoardDto> showBoardsbyUserEmail(String email);
	
	@Modifying
	@Query(value="UPDATE board SET delete_status=1 WHERE workspace_id=?1 ",nativeQuery=true)
	public void deleteBoardByWorkspaceId(Long workspaceId);
	
	@Query(value = "SELECT * FROM board WHERE workspace_id=? ", nativeQuery = true)
	public List<BoardDto> showBoardsbyWorkspaceId(Long workspaceId);
}
