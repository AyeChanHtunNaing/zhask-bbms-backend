package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbms.dto.ActivityDto;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityDto, Long>{
	
	@Query(value="SELECT * FROM activity WHERE task_Id=? ",nativeQuery = true)
    public List<ActivityDto> getActivityByTask(Long taskId);
}
