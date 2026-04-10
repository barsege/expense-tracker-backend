package com.barsege.expensetracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barsege.expensetracker.dto.CategoryResponse;
import com.barsege.expensetracker.dto.CreateCategoryRequest;
import com.barsege.expensetracker.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
    public List<CategoryResponse> getCategories(@RequestParam Long userId) {
        return categoryService.getCategoriesByUser(userId);
    }
	
	@GetMapping("/{id}")
	public CategoryResponse getCategory(@PathVariable Long id, @RequestParam Long userId) {
		return categoryService.getCategoryById(userId, id);
	}
	
	@PostMapping
	public CategoryResponse createCategory(@RequestBody CreateCategoryRequest request) {
		return categoryService.createCategory(request.userId(), request.name(), request.type());
	}
}
