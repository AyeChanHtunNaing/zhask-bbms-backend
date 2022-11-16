package com.bbms.repository;

import java.time.LocalDate;
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
	public String getTokenById(long id);

	@Query (value = "SELECT * FROM user WHERE id = ?1 AND delete_status = 0 ", nativeQuery = true)
	public UserDto getById(long id);
	
	@Query (value = "SELECT * FROM user WHERE email = ?1 AND delete_status = 0 ", nativeQuery = true)
	public UserDto getByEmail(String email);
	
	@Modifying
	@Query(value="UPDATE user SET name=?1,user_name=?2,email=?3,create_at=?4,profile=?5,picture_name=?6 WHERE id=?7 ",nativeQuery=true)
	public void updateUser(String name,String userName,String email,LocalDate createAt,byte[] proflie,String pictureName,Long userId);
}
