package com.rt.security.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class loginResponse {

	private String userName;
	private String authenticationToken;
	private String role;
	private Object userDetails;
}
