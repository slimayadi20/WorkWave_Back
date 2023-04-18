package com.example.workwave.repositories;
import com.example.workwave.entities.BankAccount;
import com.example.workwave.entities.Budget;
import com.example.workwave.entities.Project;
import com.example.workwave.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    Budget getBudgetByProject(Project project);
    Budget getBudgetByBankAccount(BankAccount bankAccount);


}
