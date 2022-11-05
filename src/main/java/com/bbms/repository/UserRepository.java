package com.bbms.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bbms.dto.UserDto;

@Repository
public interface UserRepository extends CrudRepository<UserDto,String>{

	public UserDto findByEmail(String email);

	@Query (value = "SELECT id FROM user WHERE EMAIL = ?1", nativeQuery = true)
	public long checkEmail(String email);

	@Query (value = "SELECT token FROM user WHERE id = ?1", nativeQuery = true)
	public String getTokenById(long id);

	@Query (value = "SELECT * FROM user WHERE id = ?1", nativeQuery = true)
	public UserDto getById(long id);
}
