/**
 * 
 */
package io.github.ttallaemideul.service.user;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRoleDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String role;
	private String desc;
	private int priority;
	
	public String getRoleNm() {
		if(role==null) return null;
		return role.replaceFirst("ROLE_", "");
	}

}
