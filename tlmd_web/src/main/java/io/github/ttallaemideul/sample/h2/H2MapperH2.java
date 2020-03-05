package io.github.ttallaemideul.sample.h2;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.github.ttallaemideul.config.database.DbH2ConnMapper;

@DbH2ConnMapper
public interface H2MapperH2 {

	public Date getServerNow();
	public List<Map<String, Object>> getUsers();
}
