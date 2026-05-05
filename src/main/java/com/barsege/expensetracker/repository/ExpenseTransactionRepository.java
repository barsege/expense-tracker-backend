package com.barsege.expensetracker.repository;

import java.time.LocalDate;
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
	Page<ExpenseTransaction> findByUser_IdAndCategory_Id(Long userId, Long categoryId, Pageable pageable);
	Optional<ExpenseTransaction> findByIdAndUser_Id(Long transactionId, Long userId);
	
	Page<ExpenseTransaction> findByUser_IdAndTransactionDateBetween (Long userId, LocalDate startDate, LocalDate endDate, Pageable pageable);
	Page<ExpenseTransaction> findByUser_IdAndTransactionDateGreaterThanEqual (Long userId, LocalDate startDate, Pageable pageable);
	Page<ExpenseTransaction> findByUser_IdAndTransactionDateLessThanEqual (Long userId, LocalDate endDate, Pageable pageable);
	
	Page<ExpenseTransaction> findByUser_IdAndCategory_IdAndTransactionDateBetween(
		    Long userId,
		    Long categoryId,
		    LocalDate startDate,
		    LocalDate endDate,
		    Pageable pageable
		);
	Page<ExpenseTransaction> findByUser_IdAndCategory_IdAndTransactionDateGreaterThanEqual(
			Long userId, 
			Long categoryId, 
			LocalDate startDate, 
			Pageable pageable
		);
	Page<ExpenseTransaction> findByUser_IdAndCategory_IdAndTransactionDateLessThanEqual(
			Long userId, 
			Long categoryId, 
			LocalDate endDate, 
			Pageable pageable
		);
	
}
