package io.github.ttallaemideul.service.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.ttallaemideul.service.user.UserDto;
import io.github.ttallaemideul.service.user.UserMapperH2;

@Service
public class AdminService {

	/**
	 * SecurityConfig에 정의된 암호생성자
	 */
	@Autowired
	@Qualifier("passwordEncoder")
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserMapperH2 userMapperH2;

	/**
	 * 현재 page 목록을 불러온다
	 * @param page 불러올 page
	 * @param rows page 당 레코드수
	 * @return
	 */
	public Map<String, Object> getUserList(int page, int rows, Map<String, Object> param) {
		int totCnt = userMapperH2.getUserListCount(param);
		if(rows*page>=totCnt) {
			page = totCnt/rows;
		}
		if(page<0) {
			page = 0;
		}
		param.put("limit", rows);
		param.put("offset", rows*page);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalCount", totCnt);
		resultMap.put("userList", userMapperH2.getUserList(param));
		resultMap.put("rows", rows);
		resultMap.put("page", page);
		return resultMap;
	}

	@Transactional("dbH2TransactionManager")
	public int insertDummyUser() {
		for (int i = 0; i < 10; i++) {
			UUID uuid = UUID.randomUUID();
			UserDto userDto = new UserDto();
			userDto.setLogin_id(uuid.toString().replace("-", "").substring(0, 16));
			userDto.setUser_name(uuid.toString().replace("-", "").substring(0, 8));
			userDto.setPwd(passwordEncoder.encode("1111"));
			userMapperH2.insertUserDto(userDto);
			userMapperH2.insertUserRoleDto(userDto.getId(), "ROLE_USER");
		}
		return 10;
	}

}
