package com.barsege.expensetracker.dto;

import com.barsege.expensetracker.entity.CategoryType;

public record CreateCategoryRequest(
	    Long userId,
	    String name,
	    CategoryType type
	) {}
