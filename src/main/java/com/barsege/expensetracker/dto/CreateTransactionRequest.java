package com.barsege.expensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTransactionRequest(
		@NotNull
		Long userId,
		@NotNull
		Long categoryId,
		@NotNull
		BigDecimal amount,
		@NotBlank
		String currency,
		@NotNull
		LocalDate transactionDate,
		String description
		) {

}
