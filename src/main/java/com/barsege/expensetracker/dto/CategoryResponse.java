package com.barsege.expensetracker.dto;

import com.barsege.expensetracker.entity.CategoryType;

public record CategoryResponse(
	Long id,
	String name,
	CategoryType type
) {}
