package com.rt.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rt.security.service.UserDetailServiceImpl;

@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	
	public static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailServiceImpl userDetailServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			String Token = getToken(request);
			//hs
			// String email = jwtProvider.getEmailFromToken(Token);
			//rsa 
			String email = jwtProvider.getUsernameFromToken(Token);
			UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email,Token, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("do filter method failed");
		}
		filterChain.doFilter(request, response);
		
	}

	private String getToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String authReq = request.getHeader("Authorization");
		if (authReq != null && authReq.startsWith("Bearer "))
			return authReq.replace("Bearer ", "");
		return null;
	}

}
