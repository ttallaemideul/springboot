/**
 * 
 */
package io.github.ttallaemideul.auth.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String role;
	private String desc;
	
}
