package com.barsege.expensetracker.dto.auth;

public record AuthResponse(
		
		String accessToken,
		String tokenType) {

}
