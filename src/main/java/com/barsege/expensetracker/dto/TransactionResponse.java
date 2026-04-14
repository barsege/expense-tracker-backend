package com.barsege.expensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.barsege.expensetracker.entity.CategoryType;

public record TransactionResponse(
		Long id,
		BigDecimal amount,
		String currency,
		LocalDate transactionDate,
		String description,
		Long categoryId,
		CategoryType type
		) {

}
