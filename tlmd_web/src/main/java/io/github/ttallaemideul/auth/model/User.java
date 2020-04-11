package io.github.ttallaemideul.auth.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private int active_yn;
	private String login_id;
	private String user_name;
	private String pwd;
	private Date reg_dt;
	private Date upd_dt;
}
