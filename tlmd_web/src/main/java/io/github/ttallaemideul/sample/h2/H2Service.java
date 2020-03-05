package io.github.ttallaemideul.sample.h2;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class H2Service {

	@Autowired
	private H2MapperH2 h2MapperH2;
	
	public Date getServerNow() {
		return h2MapperH2.getServerNow();
	}
	
	public List<Map<String, Object>> getUsers() {
		return h2MapperH2.getUsers();
	}
}
