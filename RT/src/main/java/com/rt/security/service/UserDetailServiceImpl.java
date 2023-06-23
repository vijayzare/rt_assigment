package com.rt.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rt.security.modal.entity.User;
import com.rt.security.modal.repo.UserRepository;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user =userRepository.findByEmail(email).get();
		
		return UserPrincipalFactory.build(user);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}