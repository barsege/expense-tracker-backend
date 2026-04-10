package com.barsege.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barsege.expensetracker.entity.ExpenseTransaction;

@Repository
public interface ExpenseTransactionRepository extends JpaRepository<ExpenseTransaction, Long>{

	List<ExpenseTransaction> findByUser_Id(Long user_Id);
	List<ExpenseTransaction> findByUser_IdAndCategory_Id(Long userId, Long categoryId);
}
