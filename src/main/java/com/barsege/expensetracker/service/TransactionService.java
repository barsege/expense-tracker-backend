package com.barsege.expensetracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barsege.expensetracker.dto.CreateTransactionRequest;
import com.barsege.expensetracker.dto.TransactionResponse;
import com.barsege.expensetracker.dto.UpdateTransactionRequest;
import com.barsege.expensetracker.entity.Category;
import com.barsege.expensetracker.entity.ExpenseTransaction;
import com.barsege.expensetracker.entity.User;
import com.barsege.expensetracker.repository.CategoryRepository;
import com.barsege.expensetracker.repository.ExpenseTransactionRepository;
import com.barsege.expensetracker.repository.UserRepository;


@Service
public class TransactionService {

	private final ExpenseTransactionRepository transactionRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;

	public TransactionService(ExpenseTransactionRepository transactionRepository, CategoryRepository categoryRepository,
			UserRepository userRepository) {
		this.transactionRepository = transactionRepository;
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository;
	}

	public TransactionResponse createTransaction(CreateTransactionRequest request) {
		User user = userRepository.findById(request.userId())
					.orElseThrow(() -> new IllegalArgumentException("User not found"));
		
		Category category = categoryRepository.findByIdAndUser_Id(request.categoryId(), request.userId())
							.orElseThrow(() -> new IllegalArgumentException("Category not found"));
		
		ExpenseTransaction expenseTransaction = new ExpenseTransaction();
		expenseTransaction.setAmount(request.amount());
		expenseTransaction.setCurrency(request.currency());
		expenseTransaction.setTransactionDate(request.transactionDate());
		expenseTransaction.setDescription(request.description());
		expenseTransaction.setUser(user);
		expenseTransaction.setCategory(category);
		
		ExpenseTransaction savedExpenseTransaction = transactionRepository.save(expenseTransaction);
		
		return mapToResponse(savedExpenseTransaction);
		
	}
	
	@Transactional(readOnly = true)
	public List<TransactionResponse> getTransactionsByUser(Long userId){
		userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		
		List<ExpenseTransaction> transactions = transactionRepository.findByUser_Id(userId);
		
		return transactions.stream()
				.map(this::mapToResponse)
				.toList();
	}
	
	@Transactional(readOnly = true)
	public TransactionResponse getTransactionById(Long userId, Long transactionId) {
		userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("User not found"));
		
		ExpenseTransaction transaction = transactionRepository.findByIdAndUser_Id(transactionId, userId)
				.orElseThrow(()-> new IllegalArgumentException("Transaction not found"));
		
		return mapToResponse(transaction);
	}
	
	public TransactionResponse updateTransaction (Long userId, Long transactionId,
													UpdateTransactionRequest request) {
		ExpenseTransaction expenseTransaction = transactionRepository.findByIdAndUser_Id(transactionId, userId)
				.orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
		
		Category category = categoryRepository.findByIdAndUser_Id(request.categoryId(), userId)
				.orElseThrow(() -> new IllegalArgumentException("Category not found"));
		
		expenseTransaction.setAmount(request.amount());
		expenseTransaction.setCurrency(request.currency());
		expenseTransaction.setTransactionDate(request.transactionDate());
		expenseTransaction.setDescription(request.description());
		expenseTransaction.setCategory(category);
		
		ExpenseTransaction savedExpenseTransaction = transactionRepository.save(expenseTransaction);
		
		return mapToResponse(savedExpenseTransaction);
	}
	
	public void deleteTransaction (Long userId, Long transactionId) {
		ExpenseTransaction expenseTransaction = transactionRepository.findByIdAndUser_Id(transactionId, userId)
				.orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
		
		transactionRepository.delete(expenseTransaction);
	}
	
	private TransactionResponse mapToResponse(ExpenseTransaction expenseTransaction) {
		return new TransactionResponse(expenseTransaction.getId(), 
				expenseTransaction.getAmount(), 
				expenseTransaction.getCurrency(), 
				expenseTransaction.getTransactionDate(),
				expenseTransaction.getDescription(),
				expenseTransaction.getCategory().getId(), 
				expenseTransaction.getCategory().getType()
				);
	}
}
