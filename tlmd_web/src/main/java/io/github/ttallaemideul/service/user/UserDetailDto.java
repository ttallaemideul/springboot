package io.github.ttallaemideul.service.user;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDetailDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String user_id;
	private UserDto user;
	private List<UserRoleDto> roles;
}
