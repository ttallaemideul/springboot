package io.github.ttallaemideul.service.user;

import java.util.List;
import java.util.Map;

import io.github.ttallaemideul.config.database.DbH2ConnMapper;

@DbH2ConnMapper
public interface UserMapperH2 {
	
	int getUserListCount(Map<String, Object> param);
	List<UserDetailDto> getUserList(Map<String, Object> param);
	int insertUserDto(UserDto userDto);
	int insertUserRoleDto(String user_id, String role);
}
