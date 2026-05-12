package com.barsege.expensetracker.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import com.barsege.expensetracker.entity.User;

@Service
public class JwtService {

	private static final String SECRET_KEY = "my-super-secret-key-for-expense-tracker-jwt-security-123456";
	private static final long EXPIRATION = 1000 * 60 * 60;
	
	private Key getSigningKey() {
		byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("role", user.getRole())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
}
