package com.barsege.expensetracker.dto;

import com.barsege.expensetracker.entity.CategoryType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryRequest(
		@NotNull
		Long userId,
		@NotBlank
		String name,
		@NotNull
		CategoryType type
) {

}
