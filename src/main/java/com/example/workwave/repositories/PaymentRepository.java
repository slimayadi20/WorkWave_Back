package com.example.workwave.repositories;

import com.example.workwave.entities.BankAccount;
import com.example.workwave.entities.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    List<Payment> findByBankAccount(BankAccount bankAccount);

}
