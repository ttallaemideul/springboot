package io.github.ttallaemideul.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.ttallaemideul.auth.model.UserDetail;
import lombok.extern.slf4j.Slf4j;
import util.Util;

@Slf4j
@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserDetailMapperH2 userDetailMapperH2;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetail ud = new UserDetail();
		ud.setUser(userDetailMapperH2.loadUserByUsername(username));
		if (Util.isEmpty(ud.getUser()))
			throw new UsernameNotFoundException("Invalid user!");
		ud.setRoles(userDetailMapperH2.getUserRole(ud.getUser().getId()));
		if (Util.isEmpty(ud.getAuthorities()))
			throw new UsernameNotFoundException("Invalid role!");
		log.debug("ud={}", ud);
		return ud;
	}

}
