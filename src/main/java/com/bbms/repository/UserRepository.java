package com.bbms.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bbms.dto.UserDto;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserDto,String>{

	public UserDto findByEmail(String email);

	@Query (value = "SELECT id FROM user WHERE EMAIL = ?1 AND delete_status = 0 ", nativeQuery = true)
	public long checkEmail(String email);

	@Query (value = "SELECT token FROM user WHERE id = ?1 AND delete_status = 0 ", nativeQuery = true)
	public String getTokenById(Long userId);

	@Query (value = "SELECT * FROM user WHERE id = ?1 AND delete_status = 0 ", nativeQuery = true)
	public UserDto getById(Long userId);
	
	@Query (value = "SELECT * FROM user WHERE email = ?1 AND delete_status = 0 ", nativeQuery = true)
	public UserDto getByEmail(String email);
	
	@Modifying
	@Query(value="UPDATE user SET name=?1,user_name=?2,profile=?3,picture_name=?4 WHERE id=?5 ",nativeQuery=true)
	public void updateUser(String name,String userName,byte[] proflie,String pictureName,Long userId);
}
