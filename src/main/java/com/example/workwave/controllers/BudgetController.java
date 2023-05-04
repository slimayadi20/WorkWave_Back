package com.example.workwave.controllers;

import com.example.workwave.entities.*;

import com.example.workwave.repositories.BankAccountRepository;
import com.example.workwave.repositories.BudgetRepository;
import com.example.workwave.repositories.ProjectRepository;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.BankAccountServiceImpl;
import com.example.workwave.services.BudgetServiceImpl;
import com.example.workwave.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    BankAccountRepository bankAccountRepository;
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
    @GetMapping("/BudgetByBankAccount/{id}")
    public Budget getBudgetByBankAccount(@PathVariable long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank Account not found"));

        Budget budget = budgetRepository.getBudgetByBankAccount(bankAccount);
        if (budget == null) {
            throw new RuntimeException("Budget not found for Bank Account");
        }
        return budget;
    }
    @GetMapping("/BudgetByProject/{id}")
    public Budget getBudgetByProject(@PathVariable long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Budget budget = budgetRepository.getBudgetByProject(project);
        if (budget == null) {
            throw new RuntimeException("Budget not found for Project");
        }
        return budget;
    }
    @PostMapping("/requestBudget")
    public ResponseEntity<Budget> requestBudget(@RequestBody Long ProjectID, @RequestParam Double Amount) {
        Budget requestedBudget = budgetService.requestBudget(ProjectID, Amount);
        return ResponseEntity.ok(requestedBudget);
    }

    @PostMapping("/approveBudget/{budgetId}")
    public ResponseEntity<Budget> approveBudget(@PathVariable Long budgetId, @RequestParam String username) {
        Budget approvedBudget = budgetService.approveBudget(budgetId, username);
        return ResponseEntity.ok(approvedBudget);
    }

    @PostMapping("/declineBudget/{budgetId}")
    public ResponseEntity<Budget> declineBudget(@PathVariable Long budgetId, @RequestParam String username) {
        Budget declinedBudget = budgetService.declineBudget(budgetId, username);
        return ResponseEntity.ok(declinedBudget);
    }
}

