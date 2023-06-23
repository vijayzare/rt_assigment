package com.rt.security.dto;

import lombok.Data;

@Data
public class UserDto {

	private int id;
	private String email;
	private String password;
	private String roles;
	private String resetToken;

}
