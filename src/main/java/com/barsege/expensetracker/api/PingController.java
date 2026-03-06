package com.barsege.expensetracker.api;

import java.time.Instant;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class PingController {
	@GetMapping(value="/api/ping")
	public Map<String, Object> ping() {
		return Map.of(
                "message", "Expense Tracker API is running",
                "timestamp", Instant.now().toString()
        );
	}

}
