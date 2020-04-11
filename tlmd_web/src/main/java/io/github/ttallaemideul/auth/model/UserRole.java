package io.github.ttallaemideul.auth.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String user_id;
	private String role_id;
}
