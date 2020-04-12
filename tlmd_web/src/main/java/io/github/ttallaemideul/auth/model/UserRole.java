/**
 * 
 */
package io.github.ttallaemideul.auth.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class UserRole implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String role;
	private String desc;
	private int priority;
	
	@Override
	public String getAuthority() {
		return role;
	}
	
}
