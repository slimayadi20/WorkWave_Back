package com.example.workwave.controllers;

import com.example.workwave.entities.*;

import com.example.workwave.repositories.*;
import com.example.workwave.services.BankAccountServiceImpl;
import com.example.workwave.services.BudgetServiceImpl;
import com.example.workwave.services.PaymentServiceImpl;
import com.example.workwave.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@RestController
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    PaymentServiceImpl paymentService;
    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/addPayment")
    public String addPayment(@RequestParam Long senderAccountId, @RequestBody Payment payment) {
        // Get the sender bank account from the database
        BankAccount senderAccount = bankAccountRepository.findById(senderAccountId).orElse(null);
        if (senderAccount == null) {
            return "Error: sender bank account not found.";
        }

        BankAccount receiverAccount = payment.getBankAccount();
        if (receiverAccount == null) {
            return "Error: receiver bank account not specified.";
        }

        Double senderNewBalance = senderAccount.getBalance() - payment.getAmountPaid();
        Double receiverNewBalance = receiverAccount.getBalance() + payment.getAmountPaid();

        senderAccount.setBalance(senderNewBalance);
        bankAccountRepository.save(senderAccount);

        receiverAccount.setBalance(receiverNewBalance);
        bankAccountRepository.save(receiverAccount);

        // Create a new transaction
        Transactions transaction = new Transactions();
        transaction.setAmount(payment.getAmountPaid());
        transaction.setBankAccount(senderAccount);
        transaction.setDescription(payment.getDescription());
        transaction.setTransactionDate(payment.getPaymentDate());


        transactionRepository.save(transaction);

        // Save the payment to the database
        payment.setBankAccount(senderAccount);
        paymentRepository.save(payment);

        return "Payment added successfully.";
    }
    @DeleteMapping("/deletePayment/{id}")
    public String deletePayment(@PathVariable long id) {
        return paymentService.deletePayment(id);
    }

    @PutMapping("/updatePayment")
    public Payment updatePayment(@RequestBody Payment payment) {
        return paymentService.updatePayment(payment);
    }

    @GetMapping("/Payments")//affichage+pagination
    public List<Payment> show() {
        return paymentService.GetAllPayments();
    }

    @GetMapping("/Payment/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }


    @GetMapping("/PaymentsByBankAccount/{idBankAccount}")
    public List<Payment> getPaymentByBankAccount(@PathVariable long idBankAccount) {
        BankAccount bankAccount = bankAccountRepository.findById(idBankAccount)
                .orElseThrow(() -> new RuntimeException("BankAccount not found"));

        return paymentRepository.findByBankAccount(bankAccount);
    }
}
