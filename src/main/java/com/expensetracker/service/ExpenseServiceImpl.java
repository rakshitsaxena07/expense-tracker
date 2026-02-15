package com.expensetracker.service;

import com.expensetracker.dto.CreateExpenseRequest;
import com.expensetracker.entity.Category;
import com.expensetracker.entity.Expense;
import com.expensetracker.entity.User;
import com.expensetracker.repository.CategoryRepository;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public Expense createExpense(CreateExpenseRequest request) {

        if (!isDateInCurrentFinancialYear(request.getTransactionDate())) {
            throw new RuntimeException("Transaction date must be within the current financial year");
        }

       
        Long userId = request.getUserId() != null ? request.getUserId() : 1L;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        
        Expense expense = Expense.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .amount(request.getAmount())
                .transactionDate(request.getTransactionDate())
                .paymentMethod(request.getPaymentMethod())
                .user(user)
                .category(category)
                .build();

  
        return expenseRepository.save(expense);
    }

    private boolean isDateInCurrentFinancialYear(LocalDate date) {
        LocalDate today = LocalDate.now();

        LocalDate startOfFY;
        LocalDate endOfFY;

        if (today.getMonthValue() >= 4) {
            startOfFY = LocalDate.of(today.getYear(), 4, 1);
            endOfFY = LocalDate.of(today.getYear() + 1, 3, 31);
        } else {
            startOfFY = LocalDate.of(today.getYear() - 1, 4, 1);
            endOfFY = LocalDate.of(today.getYear(), 3, 31);
        }

        return (date.isEqual(startOfFY) || date.isAfter(startOfFY)) &&
               (date.isEqual(endOfFY) || date.isBefore(endOfFY));
    }
}
