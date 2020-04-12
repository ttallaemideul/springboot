package io.github.ttallaemideul.auth;

import java.util.List;

import io.github.ttallaemideul.auth.model.UserDetail;
import io.github.ttallaemideul.auth.model.UserRole;
import io.github.ttallaemideul.config.database.DbH2ConnMapper;

@DbH2ConnMapper
public interface UserDetailMapperH2 {
	UserDetail loadUserByUsername(String login_id);
	List<UserRole> getUserRole(String user_id);
}
