package io.github.ttallaemideul.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.ttallaemideul.service.user.UserDetailDto;
import io.github.ttallaemideul.service.user.UserMapperH2;

@Service
public class AdminService {

	@Autowired
	private UserMapperH2 userMapperH2;
	
	public List<UserDetailDto> getUserList() {
		return userMapperH2.getUserList();
	}
	
	
}
