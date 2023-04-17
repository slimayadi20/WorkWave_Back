package com.example.workwave.controllers;

import com.example.workwave.entities.*;

import com.example.workwave.repositories.*;
import com.example.workwave.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping("/addTransaction")
    public String addTransaction(@RequestBody Transactions transactions) {
        return transactionService.addTransaction(transactions);
    }

    @DeleteMapping("/deleteTransaction/{id}")
    public String deleteTransaction(@PathVariable long id) {
        return transactionService.deleteTransaction(id);
    }

    @PutMapping("/updateTransaction")
    public Transactions updateTransaction(@RequestBody Transactions transactions) {
        return transactionService.updateTransaction(transactions);
    }

    @GetMapping("/Transactions")//affichage+pagination
    public List<Transactions> show() {
        return transactionService.GetAllTransactions();
    }

    @GetMapping("/Transaction/{id}")
    public Transactions getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

}
