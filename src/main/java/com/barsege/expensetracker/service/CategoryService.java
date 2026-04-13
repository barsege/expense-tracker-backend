package com.barsege.expensetracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.barsege.expensetracker.dto.CategoryResponse;
import com.barsege.expensetracker.entity.Category;
import com.barsege.expensetracker.entity.CategoryType;
import com.barsege.expensetracker.entity.User;
import com.barsege.expensetracker.repository.CategoryRepository;
import com.barsege.expensetracker.repository.UserRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;
	
	public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository;
	}
	
	public CategoryResponse createCategory (Long userId, String name, CategoryType type) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
		
		if(categoryRepository.existsByUser_IdAndName(userId, name)) {
			throw new IllegalArgumentException("Category already exists for this user");
		}
		
		Category category = new Category();
		category.setName(name);
		category.setType(type);
		category.setUser(user);
		Category savedCategory = categoryRepository.save(category);
		return mapToResponse(savedCategory);
		
	}
	
	public List<CategoryResponse> getCategoriesByUser(Long userId){
		userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
		List<Category> categories = categoryRepository.findByUser_Id(userId);
		
		return categories
				.stream()
				.map(this::mapToResponse)
				.toList();
	}
	
	public CategoryResponse getCategoryById(Long userId, Long categoryId){
		Category category = categoryRepository
				.findByIdAndUser_Id(categoryId, userId)
				.orElseThrow(() -> new IllegalArgumentException("Category not found"));
		
		return mapToResponse(category);
	}
	
	public CategoryResponse updateCategory(Long userId, Long categoryId, String name, CategoryType type) {
		Category category = categoryRepository
				.findByIdAndUser_Id(categoryId, userId)
				.orElseThrow(() -> new IllegalArgumentException("Category not found"));
		
		if(!category.getName().equals(name) && categoryRepository.existsByUser_IdAndName(userId, name)) {
			throw new IllegalArgumentException("Category already exists for this user");
		}
		
		category.setName(name);
		category.setType(type);
		Category savedCategory = categoryRepository.save(category);	
		return mapToResponse(savedCategory);
		
		
	}
	
	public void deleteCategory (Long userId, Long categoryId) {
		Category category = categoryRepository.findByIdAndUser_Id(categoryId, userId)
							.orElseThrow(() -> new IllegalArgumentException("Category Not Found"));
		
		categoryRepository.delete(category);
	}
	
	private CategoryResponse mapToResponse (Category category) {
		return new CategoryResponse(
				category.getId(), 
				category.getName(),
				category.getType()
				);
	}
}
