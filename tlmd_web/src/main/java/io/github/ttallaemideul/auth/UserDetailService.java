package io.github.ttallaemideul.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.ttallaemideul.auth.model.UserDetail;
import io.github.ttallaemideul.config.SysProperty;
import tlmd.Util;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserDetailMapperH2 userDetailMapperH2;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetail ud = userDetailMapperH2.loadUserByUsername(username);
		if (Util.isEmpty(ud))
			throw new UsernameNotFoundException(SysProperty.AUTH_ERROR_USERNOTFOUND);
		return ud;
	}

}
