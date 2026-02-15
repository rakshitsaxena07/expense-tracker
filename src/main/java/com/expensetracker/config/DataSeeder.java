package com.expensetracker.config;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.User;
import com.expensetracker.repository.CategoryRepository;
import com.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        // Seed default user
        if (userRepository.count() == 0) {
            User user = new User(null, "Default User", "default@user.com");
            userRepository.save(user);
        }

        // Seed predefined categories
        List<String> categories = List.of(
                "EMI", "FOOD", "TRANSPORT", "HEALTHCARE", "RENT", "OTHER"
        );

        for (String name : categories) {
            if (!categoryRepository.existsByName(name)) {
                categoryRepository.save(new Category(null, name));
            }
        }
    }
}