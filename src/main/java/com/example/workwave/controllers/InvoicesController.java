package com.example.workwave.controllers;

import com.example.workwave.entities.*;

import com.example.workwave.repositories.*;
import com.example.workwave.services.BankAccountServiceImpl;
import com.example.workwave.services.BudgetServiceImpl;
import com.example.workwave.services.InvoicesServiceImpl;
import com.example.workwave.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@RestController
public class InvoicesController {
    @Autowired
    InvoicesRepository invoicesRepository;
    @Autowired
    InvoicesServiceImpl invoicesService;
    @Autowired
    BankAccountRepository bankAccountRepository;

    @PostMapping("/addInvoice")
    public String addInvoice(@RequestBody Invoices invoices) {
        return invoicesService.addInvoice(invoices);
    }

    @DeleteMapping("/deleteInvoice/{id}")
    public String deleteInvoice(@PathVariable long id) {
        return invoicesService.deleteInvoice(id);
    }

    @PutMapping("/updateInvoice")
    public Invoices updateInvoice(@RequestBody Invoices invoices) {
        return invoicesService.updateInvoice(invoices);
    }

    @GetMapping("/Invoices")//affichage+pagination
    public List<Invoices> show() {
        return invoicesService.GetAllInvoices();
    }

    @GetMapping("/Invoice/{id}")
    public Invoices getInvoiceById(@PathVariable Long id) {
        return invoicesService.getInvoiceById(id);
    }
    @GetMapping("/InvoicesbyBankAccount/{id}")
    public List<Invoices> getInvoiceByBank(@PathVariable Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank Account not found"));

        List<Invoices> invoices = invoicesRepository.getInvoicesByBankAccount(bankAccount);
        if (invoices == null) {
            throw new RuntimeException("Transaction not found for Bank Account");
        }
        return invoices;
    }

}
