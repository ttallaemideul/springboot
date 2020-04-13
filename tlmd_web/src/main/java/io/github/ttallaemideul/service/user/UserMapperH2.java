package io.github.ttallaemideul.service.user;

import java.util.List;

import io.github.ttallaemideul.config.database.DbH2ConnMapper;

@DbH2ConnMapper
public interface UserMapperH2 {
	
	List<UserDetailDto> getUserList();
}
