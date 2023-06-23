package com.rt.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.rt.security.modal.entity.User;

public class UserPrincipalFactory {
	
	public static UserPrincipal build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRoleNumber().name())).collect(Collectors.toList());

		return new UserPrincipal(user.getEmail(), user.getPassword(), authorities);
	}
}
