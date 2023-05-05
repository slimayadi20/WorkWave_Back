package com.example.workwave.controllers;

import com.example.workwave.entities.*;

import com.example.workwave.repositories.*;
import com.example.workwave.services.BankAccountServiceImpl;
import com.example.workwave.services.BudgetServiceImpl;
import com.example.workwave.services.PaymentServiceImpl;
import com.example.workwave.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
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
    UserRepository userRepository;
    @Autowired
    PaymentServiceImpl paymentService;

    @Autowired
    TransactionRepository transactionRepository;

  //@PostMapping("/addPayment")
  //public String addPayment(@RequestParam Long senderAccountId, @RequestBody Payment payment) {
  //    // Get the sender bank account from the database
  //    BankAccount senderAccount = bankAccountRepository.findById(senderAccountId).orElse(null);
  //    if (senderAccount == null) {
  //        return "Error: sender bank account not found.";
  //    }

  //    BankAccount receiverAccount = bankAccountRepository.findById(payment.getBankAccount().getId()).get();
  //    if (receiverAccount == null) {
  //        return "Error: receiver bank account not specified.";
  //    }
  //    Double amountPaid = payment.getAmountPaid();

  //    Transactions senderTransaction = new Transactions();
  //    senderTransaction.setAmount(-amountPaid);
  //    senderTransaction.setBankAccount(senderAccount);
  //    senderTransaction.setDescription(payment.getDescription());
  //    senderTransaction.setTransactionDate(payment.getPaymentDate());


  //    Double senderNewBalance = senderAccount.getBalance() - amountPaid;
  //    senderAccount.setBalance(senderNewBalance);
  //    bankAccountRepository.save(senderAccount);



  //    Transactions receiverTransaction = new Transactions();
  //    receiverTransaction.setAmount(amountPaid);
  //    receiverTransaction.setBankAccount(receiverAccount);
  //    receiverTransaction.setDescription(payment.getDescription());
  //    receiverTransaction.setTransactionDate(payment.getPaymentDate());


  //    Double receiverNewBalance = receiverAccount.getBalance() + amountPaid;
  //    receiverAccount.setBalance(receiverNewBalance);
  //    bankAccountRepository.save(receiverAccount);



  //    transactionRepository.save(senderTransaction);
  //    transactionRepository.save(receiverTransaction);
  //    payment.setBankAccount(senderAccount);
  //    paymentRepository.save(payment);

  //    return "Payment added successfully.";
  //}
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

        return paymentRepository.findByBankAccount_Id(idBankAccount);
    }
    @PostMapping("/Paysalary")
    public ResponseEntity<String> paySalary(
            @RequestParam("userId") String userId,
            @RequestParam("senderBankAccountId") Long senderBankAccountId,
            @RequestParam("receiverBankAccountId") Long receiverBankAccountId
    ) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        try {
            paymentService.paySalary(user, senderBankAccountId, receiverBankAccountId);
            return ResponseEntity.ok("Payment successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//    ----------------- STATISTICSSSS ----------------------

    @GetMapping("/totalAmountPaidBySenderBankAccountId")
    public ResponseEntity<Double> getTotalAmountPaidBySenderBankAccountId(@RequestParam Long senderBankAccountId) {
        Double totalAmountPaid = paymentRepository.getTotalAmountPaidBySenderBankAccountId(senderBankAccountId);
        return ResponseEntity.ok(totalAmountPaid);
    }
    @GetMapping("/{senderBankAccountId}/payments-this-month")
    public ResponseEntity<List<Double>> getPaymentsBySenderBankAccountIdThisMonth(@PathVariable Long senderBankAccountId) {
        List<Double> payments = paymentRepository.getPaymentsBySenderBankAccountIdThisMonth(senderBankAccountId);
        return ResponseEntity.ok().body(payments);
    }
    @GetMapping("/{senderBankAccountId}/payments-today")
    public ResponseEntity<List<Double>> getPaymentsBySenderBankAccountIdToday(@PathVariable Long senderBankAccountId) {
        List<Double> payments = paymentRepository.getPaymentsBySenderBankAccountIdToday(senderBankAccountId);
        return ResponseEntity.ok().body(payments);
    }
  //  @GetMapping("/{senderBankAccountId}/payment-percentage-change")
  //  public ResponseEntity<Double> getPaymentPercentageChange(@PathVariable Long senderBankAccountId) {
  //      LocalDate yesterday = LocalDate.now().minusDays(1);
//
  //      Date yesterdayDate = Date.from(yesterday.atStartOfDay(ZoneId.systemDefault()).toInstant());
  //      Double totalAmountPaidToday = paymentRepository.getTotalAmountPaidToday(senderBankAccountId);
  //      Double totalAmountPaidYesterday = paymentRepository.getTotalAmountPaidYesterday(senderBankAccountId,yesterdayDate);
//
  //      if (totalAmountPaidYesterday == null || totalAmountPaidYesterday == 0) {
  //          return ResponseEntity.badRequest().body(null);
  //      }
//
  //      Double difference = totalAmountPaidToday - totalAmountPaidYesterday;
  //      Double percentageChange = (difference / totalAmountPaidYesterday) * 100.0;
//
  //      return ResponseEntity.ok().body(percentageChange);
  //  }






}