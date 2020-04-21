package io.github.ttallaemideul.service.admin;

import java.util.HashMap;
import java.util.Map;

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
	 * 
	 * @param page 불러올 page. 1부터 시작
	 * @param rows page 당 레코드수
	 * @return
	 */
	public Map<String, Object> getUserList(int page, int rows, Map<String, Object> param) {
		int totCnt = userMapperH2.getUserListCount(param);
		page = page>0 ? page-1 : 0;
		if (rows * page >= totCnt) {
			page = totCnt / rows;
		}
		param.put("limit", rows);
		param.put("offset", rows * page); // 쿼리에서는 page=0부터 시작
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalCount", totCnt);
		resultMap.put("userList", userMapperH2.getUserList(param));
		resultMap.put("rows", rows);
		resultMap.put("page", (page+1)); // 화면에는 page=1부터 시작
		return resultMap;
	}

	@Transactional("dbH2TransactionManager")
	public int insertDummyUser() {
		int totCnt = userMapperH2.getUserListCount(null);
		for (int i = 0; i < 40; i++) {
			UserDto userDto = new UserDto();
			userDto.setLogin_id(String.format("user%03d", totCnt+i));
			userDto.setUser_name(String.format("name%03d", totCnt+i));
			userDto.setPwd(passwordEncoder.encode("1111"));
			userMapperH2.insertUserDto(userDto);
			userMapperH2.insertUserRoleDto(userDto.getId(), "ROLE_USER");
		}
		return 40;
	}

}
