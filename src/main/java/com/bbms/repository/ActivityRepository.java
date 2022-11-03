package com.bbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbms.dto.ActivityDto;

public interface ActivityRepository extends JpaRepository<ActivityDto, Long>{
	
	
}
