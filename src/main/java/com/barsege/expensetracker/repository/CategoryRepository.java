package com.barsege.expensetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barsege.expensetracker.entity.Category;
import com.barsege.expensetracker.entity.CategoryType;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	boolean existsByUser_IdAndName(Long userId, String name);
	List<Category> findByUser_Id(Long userId);
	List<Category> findByUser_IdAndType(Long userId, CategoryType type);
	Optional<Category> findByIdAndUser_Id(Long categoryId, Long userId);
}
