package com.expensetracker.controller;

import com.expensetracker.dto.CreateExpenseRequest;
import com.expensetracker.entity.Expense;
import com.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody CreateExpenseRequest request) {
        Expense expense = expenseService.createExpense(request);
        return ResponseEntity.status(201).body(expense);
        //return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }
}
