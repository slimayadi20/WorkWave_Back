package com.example.workwave.services;

import com.example.workwave.entities.*;
import com.example.workwave.repositories.BankAccountRepository;
import com.example.workwave.repositories.BudgetRepository;
import com.example.workwave.repositories.ProjectRepository;
import com.example.workwave.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class BudgetServiceImpl {
    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    ServletContext context;

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;

    public Budget requestBudget(Long pID,Double Amount,Long bID) {
        Project project = projectRepository.findById(pID)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        BankAccount bankAccount= bankAccountRepository.findById(bID)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Budget budget = new Budget();
        budget.setName(project.getProjectname() + " Budget");
        budget.setAmount(Amount);
        budget.setStartDate(new Date());
        budget.setEndDate(project.getDateExpiration());
        budget.setNotes(project.getDescription());
        budget.setStatusBudget(StatusBudget.InProgress);
        budget.setProject(project);
        budget.setBankAccount(bankAccount);
        project.setBudget(budget);

        budgetRepository.save(budget);


        return budget;
    }

    public Budget approveBudget(Long budgetId) {


        Budget budget = budgetRepository.findById(budgetId).orElse(null);
        if (budget == null) {
            throw new RuntimeException("Budget not found");
        }

        BankAccount bankAccount = budget.getBankAccount();
        bankAccount.setBalance(bankAccount.getBalance()- budget.getAmount());



        budget.setStatusBudget(StatusBudget.Approved);
        budgetRepository.save(budget);

        return budget;
    }

    public Budget declineBudget(Long budgetId) {


        Budget budget = budgetRepository.findById(budgetId).orElse(null);
        if (budget == null) {
            throw new RuntimeException("Budget not found");
        }

        budget.setStatusBudget(StatusBudget.Declined);
        budgetRepository.save(budget);

        return budget;
    }

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