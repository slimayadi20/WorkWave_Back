package com.example.workwave.repositories;
import com.example.workwave.entities.Budget;
import com.example.workwave.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    List<Budget> findByUser(User user);

}
