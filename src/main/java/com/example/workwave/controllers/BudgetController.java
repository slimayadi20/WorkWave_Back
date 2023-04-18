package com.example.workwave.controllers;

import com.example.workwave.entities.BankAccount;

import com.example.workwave.entities.Budget;
import com.example.workwave.entities.User;

import com.example.workwave.entities.holiday;
import com.example.workwave.repositories.BankAccountRepository;
import com.example.workwave.repositories.BudgetRepository;
import com.example.workwave.repositories.ProjectRepository;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.BankAccountServiceImpl;
import com.example.workwave.services.BudgetServiceImpl;
import com.example.workwave.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@RestController
public class BudgetController {

    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    BudgetServiceImpl budgetService;

    @PostMapping("/addBudget")
    public String addBudget(@RequestBody Budget budget) {
        return budgetService.addBudget(budget);
    }

    @DeleteMapping("/deleteBudget/{id}")
    public String deleteBudget(@PathVariable long id) {
        return budgetService.deleteBudget(id);
    }

    @PutMapping("/updateBudget")
    public Budget updateBudget(@RequestBody Budget budget) {
        return budgetService.updateBudget(budget);
    }

    @GetMapping("/Budgets")//affichage+pagination
    public List<Budget> show() {
        return budgetService.GetAllBudgets();
    }

    @GetMapping("/Budget/{id}")
    public Budget getBudgetById(@PathVariable Long id) {
        return budgetService.getBudgetById(id);
    }

}

