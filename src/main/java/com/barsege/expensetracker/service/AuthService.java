package com.barsege.expensetracker.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.barsege.expensetracker.dto.auth.AuthResponse;
import com.barsege.expensetracker.dto.auth.RegisterRequest;
import com.barsege.expensetracker.entity.User;
import com.barsege.expensetracker.repository.UserRepository;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
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
		
		userRepository.save(user);
		
		return new AuthResponse("dummy-token", "Bearer");
	}
}
