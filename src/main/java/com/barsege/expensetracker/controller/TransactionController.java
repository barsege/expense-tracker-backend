package com.barsege.expensetracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barsege.expensetracker.dto.CreateTransactionRequest;
import com.barsege.expensetracker.dto.TransactionResponse;
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
	
}
