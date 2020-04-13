package io.github.ttallaemideul.service.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private byte active_yn;
	private byte expired_yn;
	private byte locked_yn;
	private byte pwd_expired_yn;
	private String login_id;
	private String user_name;
	private String pwd;
	private Date reg_dt;
	private Date upd_dt;
}
