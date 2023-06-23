package com.rt.security.jwt;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	

	@Value("${jwt.secret}")
	String secret;
	@Value("${jwt.expiration}")
	int expiration;



	public String generateToken(String subject, long expirationTime)
			throws IOException, InvalidKeySpecException, InvalidKeyException, NoSuchAlgorithmException {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expirationTime);
		return Jwts.builder().setSubject(subject).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secret).compact();
//				.signWith(SignatureAlgorithm.RS256, getPrivateKey()).compact();
	}

	public boolean validateToken(String token) throws IOException, InvalidKeySpecException {
		try {
//			Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token);
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			// Handle token validation exception if necessary
		}
		return false;
	}


	public String getUsernameFromToken(String token)
			throws IOException, InvalidKeySpecException, SignatureException, ExpiredJwtException,
			UnsupportedJwtException, MalformedJwtException, IllegalArgumentException, NoSuchAlgorithmException {
//		Claims claims = Jwts.parser().setSigningKey(generateKeyPair().getPublic()).parseClaimsJws(token).getBody();
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}



}
