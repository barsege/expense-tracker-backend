package com.barsege.expensetracker.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barsege.expensetracker.dto.CreateTransactionRequest;
import com.barsege.expensetracker.dto.TransactionResponse;
import com.barsege.expensetracker.dto.UpdateTransactionRequest;
import com.barsege.expensetracker.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping
	public TransactionResponse createTransaction(@Valid @RequestBody CreateTransactionRequest request) {
		return transactionService.createTransaction(request);
	}
	
	@GetMapping
	public Page<TransactionResponse> getTransactions(@RequestParam Long userId, @RequestParam(defaultValue = "0") int page,
														@RequestParam(defaultValue = "10") int size,
														@RequestParam(defaultValue = "transactionDate") String sortBy,
														@RequestParam(defaultValue = "desc") String direction,
														@RequestParam(required = false) Long categoryId) {
		return transactionService.getTransactionsByUser(userId, page, size, sortBy, direction, categoryId);
	}
	
	@GetMapping("/{id}")
	public TransactionResponse getTransaction(@PathVariable Long id ,@RequestParam Long userId) {
		return transactionService.getTransactionById(userId, id);
	}
	
	@PutMapping("/{id}")
	public TransactionResponse updateTransaction(@PathVariable Long id,
													@Valid @RequestBody UpdateTransactionRequest request) {
		return transactionService.updateTransaction(request.userId(), id, request);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTransaction(@PathVariable Long id, @RequestParam Long userId) {
		transactionService.deleteTransaction(userId, id);
	}
	
	
}
