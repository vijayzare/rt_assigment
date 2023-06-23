package com.rt.security.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rt.security.dto.UserDto;
import com.rt.security.jwt.JwtProvider;
import com.rt.security.modal.entity.Role;
import com.rt.security.modal.entity.User;
import com.rt.security.modal.enums.RoleNumber;
import com.rt.security.modal.repo.RoleRepository;
import com.rt.security.modal.repo.UserRepository;
import com.rt.security.response.loginResponse;

import io.jsonwebtoken.security.InvalidKeyException;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired(required = true)
	private JwtProvider jwtProvider;

	@Autowired
	private RoleRepository roleRepository;
    private final long expirationTime = 123000;
	@PostMapping("/login")
	public loginResponse login(@RequestBody UserDto user) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, java.security.InvalidKeyException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		
		// hs
	//	String jwt = jwtProvider.generateToken(authentication);
		
		//rsa
		String jwt = 
				jwtProvider.generateToken(user.getEmail(), expirationTime);
		loginResponse responce = new loginResponse();
		Optional<User> userOp = userRepository.findByEmail(user.getEmail());

		responce.setAuthenticationToken(jwt);
		responce.setUserDetails(userOp.get());
		return responce;
	}

	@PostMapping("/signup")
	public String addUser(@RequestBody UserDto dto) {

		Optional<User> userEmail = userRepository.findByEmail(dto.getEmail());
		if (userEmail.isPresent()) {
			return "Email is allredy exist";
		}

		User user = new User();
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		Set<Role> roles = new HashSet<>();
		Role roleUser = roleRepository.findByRoleNumber(RoleNumber.ROLE_USER);
		user.getRoles().add(roleUser);
		userRepository.save(user);
		return "Signup  Succesfully... please Long in ";

	}


	
}
