package com.barsege.expensetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barsege.expensetracker.entity.ExpenseTransaction;

@Repository
public interface ExpenseTransactionRepository extends JpaRepository<ExpenseTransaction, Long>{

	Page<ExpenseTransaction> findByUser_Id(Long userId, Pageable pageable);
	List<ExpenseTransaction> findByUser_IdAndCategory_Id(Long userId, Long categoryId);
	Optional<ExpenseTransaction> findByIdAndUser_Id(Long transactionId, Long userId);
}
