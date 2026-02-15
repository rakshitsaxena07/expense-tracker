package com.expensetracker.service;
import com.expensetracker.dto.CreateExpenseRequest;
import com.expensetracker.entity.Expense;

public interface ExpenseService {
    Expense createExpense(CreateExpenseRequest request);
}