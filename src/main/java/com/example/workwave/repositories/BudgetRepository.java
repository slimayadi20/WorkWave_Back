package com.example.workwave.repositories;
import com.example.workwave.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    Budget getBudgetByProject(Project project);
    Budget getBudgetByBankAccount(BankAccount bankAccount);
    List<Budget> findByStatusBudget(StatusBudget statusBudget);

}
