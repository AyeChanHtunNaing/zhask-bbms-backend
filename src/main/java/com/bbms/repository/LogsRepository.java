package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bbms.dto.LogsDto;

@Transactional
public interface LogsRepository extends JpaRepository<LogsDto, Long>{

	@Query(value="SELECT * FROM logs WHERE task_id=?",nativeQuery = true)
	public List<LogsDto> getLogsByTask(Long taskId);
	

}
