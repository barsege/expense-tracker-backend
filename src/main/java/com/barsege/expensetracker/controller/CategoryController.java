package com.barsege.expensetracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barsege.expensetracker.dto.CategoryResponse;
import com.barsege.expensetracker.dto.CreateCategoryRequest;
import com.barsege.expensetracker.dto.UpdateCategoryRequest;
import com.barsege.expensetracker.service.CategoryService;

import jakarta.validation.Valid;

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
	public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest request) {
		return categoryService.createCategory(request.userId(), request.name(), request.type());
	}
	
	@PutMapping("/{id}")
	public CategoryResponse updateCategory(@PathVariable Long id,
											@Valid @RequestBody UpdateCategoryRequest request) {
		return categoryService.updateCategory(request.userId(), id, request.name(), request.type());
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable Long id, @RequestParam Long userId) {
		categoryService.deleteCategory(userId, id);
	}
}
