package com.example.workwave.services;

import com.example.workwave.entities.Budget;
import com.example.workwave.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

public class BudgetServiceImpl {
    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    ServletContext context;

    public List<Budget> GetAllBudgets() {
        return budgetRepository.findAll();
    }

    public String addBudget(Budget b)  {
        budgetRepository.save(b);
        return "ok" ;
    }

    public String deleteBudget(Long idBudget) {
        budgetRepository.deleteById(idBudget);
        return "Budget removed !! " + idBudget;
    }


    //the update method
    public Budget updateBudget(Budget budget) {
        Budget existingBudget = budgetRepository.findById(budget.getId()).orElse(null);
        existingBudget.setBankAccount(budget.getBankAccount());
        existingBudget.setAmount(budget.getAmount());
        existingBudget.setName(budget.getName());
        existingBudget.setStartDate(budget.getStartDate());
        existingBudget.setEndDate(budget.getEndDate());
        existingBudget.setNotes(budget.getNotes());
        existingBudget.setProject(budget.getProject());
        existingBudget.setId(budget.getId());



        return budgetRepository.save(existingBudget);
    }
    public Budget getBudgetById(Long id) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);
        if (optionalBudget.isPresent()) {
            return optionalBudget.get();
        } else {
            throw new EntityNotFoundException("Bank Account not found with id " + id);
        }
    }

}