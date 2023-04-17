package com.example.workwave.controllers;

import com.example.workwave.entities.BankAccount;

import com.example.workwave.entities.User;

import com.example.workwave.entities.holiday;
import com.example.workwave.repositories.BankAccountRepository;
import com.example.workwave.repositories.ProjectRepository;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.BankAccountServiceImpl;
import com.example.workwave.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class BankAccountController {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BankAccountServiceImpl bankAccountService;

    @PostMapping("/addBankAccount")
    public String addBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.addBankAccount(bankAccount);
    }

    @DeleteMapping("/deleteBankAccount/{id}")
    public String deleteBankAccount(@PathVariable long id) {
        return bankAccountService.deleteBankAccount(id);
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

}
