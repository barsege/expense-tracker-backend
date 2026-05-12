package com.barsege.expensetracker.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.barsege.expensetracker.dto.auth.AuthResponse;
import com.barsege.expensetracker.dto.auth.LoginRequest;
import com.barsege.expensetracker.dto.auth.RegisterRequest;
import com.barsege.expensetracker.entity.User;
import com.barsege.expensetracker.repository.UserRepository;
import com.barsege.expensetracker.security.JwtService;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	
	public AuthResponse register (RegisterRequest request) {
		if(userRepository.existsByEmail(request.email())) {
			throw new IllegalArgumentException("Email already in use");
		}
		
		String hashedPassword = passwordEncoder.encode(request.password());
		
		User user = new User();
		
		user.setName(request.name());
		user.setEmail(request.email());
		user.setPasswordHash(hashedPassword);
		user.setRole("USER");
		
		User savedUser = userRepository.save(user);
		
		String token = jwtService.generateToken(savedUser);		
		return new AuthResponse(token, "Bearer");
	}
	
	public AuthResponse login (LoginRequest loginRequest) {
		User user = userRepository.findByEmail(loginRequest.email())
			.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		
		if(!passwordEncoder.matches(loginRequest.password(), user.getPasswordHash())) {
			throw new IllegalArgumentException("Invalid email or password");
		}
		
		String token = jwtService.generateToken(user);
		return new AuthResponse(token, "Bearer");
			
	}
}
