package com.bbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bbms.dto.UserDto;

@Repository
public interface UserRepository extends CrudRepository<UserDto,String>{

	public UserDto findByEmail(String email);

	@Query (value = "SELECT id FROM user WHERE EMAIL = ?1 AND delete_status = 0 ", nativeQuery = true)
	public long checkEmail(String email);

	@Query (value = "SELECT token FROM user WHERE id = ?1 AND delete_status = 0 ", nativeQuery = true)
	public String getTokenById(long id);

	@Query (value = "SELECT * FROM user WHERE id = ?1 AND delete_status = 0 ", nativeQuery = true)
	public UserDto getById(long id);
	
	@Query (value = "SELECT * FROM user WHERE email = ?1 AND delete_status = 0 ", nativeQuery = true)
	public UserDto getByEmail(String email);
	
}
