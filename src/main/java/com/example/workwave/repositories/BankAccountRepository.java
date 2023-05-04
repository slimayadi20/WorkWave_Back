package com.example.workwave.repositories;
import com.example.workwave.entities.BankAccount;
import com.example.workwave.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    BankAccount findByUserUserName(String user);
    List<BankAccount> findByStatusIsTrue();
}
