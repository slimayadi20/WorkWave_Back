package com.example.workwave.controllers;

import com.example.workwave.entities.BankAccount;

import com.example.workwave.entities.Budget;
import com.example.workwave.entities.User;

import com.example.workwave.entities.holiday;
import com.example.workwave.repositories.*;
import com.example.workwave.services.BankAccountServiceImpl;
import com.example.workwave.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@RestController
public class BankAccountController {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BankAccountServiceImpl bankAccountService;
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    InvoicesRepository invoicesRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/addBankAccount")
    public String addBankAccount(@RequestBody
    BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);

        // If the bank account has a user, update the user entity with the new bank account
        if (bankAccount.getUser() != null) {
            User user = userRepository.findById(bankAccount.getUser().getUserName())
                    .orElseThrow(() -> new IllegalArgumentException("No user found with userName: " + bankAccount.getUser().getUserName()));
            user.setBankAccount(bankAccount);
            userRepository.save(user);
        }
        return bankAccountService.addBankAccount(bankAccount);

    }

    @DeleteMapping("/deleteBankAccount/{id}")
    public String deleteBankAccount(@PathVariable long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bank account ID: " + id));

            if (bankAccount.getUser() != null) {
                // Find the user that has the bank account
                User user = userRepository.findByBankAccount(bankAccount);

                // Set the BankAccount field to null
                user.setBankAccount(null);

                // Save the updated user entity
                userRepository.save(user);
            }


        // Delete the bank account entity
        bankAccountRepository.delete(bankAccount);

        return "Bank account removed: " + id;
    }

    @PutMapping("/updateBankAccount")
    public BankAccount updateBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.updateBankAccount(bankAccount);
    }

    @GetMapping("/BankAccount")//affichage+pagination
    public List<BankAccount> showBankAccount() {
        return bankAccountService.GetAllBankAccounts();
    }

    @GetMapping("/BankAccount/{id}")
    public BankAccount getBankAccountById(@PathVariable Long id) {
        return bankAccountService.getBankAccountById(id);
    }
    @GetMapping("/BankAccountByUser/{userName}")
    public BankAccount getBankAccountByUser(@PathVariable String userName) {
        User user = userRepository.findById(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.getUserName());
        BankAccount bankAccount = bankAccountRepository.findByUserUserName(user.getUserName());
        if (bankAccount == null) {
            throw new RuntimeException("Bank account not found for user");
        }
        System.out.println(bankAccount.getAccountName());
        return bankAccount;
    }
}
