package com.example.workwave.services;

import com.example.workwave.entities.*;
import com.example.workwave.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class PaymentServiceImpl {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    ServletContext context;

    public List<Payment> GetAllPayments() {
        return paymentRepository.findAll();
    }

    public String addPayment(Payment P)  {
        paymentRepository.save(P);
        return "ok" ;
    }

    public String deletePayment(Long idPay) {
        paymentRepository.deleteById(idPay);
        return "Budget removed !! " + idPay;
    }


    //the update method
    public Payment updatePayment(Payment payment) {
        Payment existingPayment = paymentRepository.findById(payment.getId()).orElse(null);
        existingPayment.setBankAccount(payment.getBankAccount());
        existingPayment.setPaymentDate(payment.getPaymentDate());
        existingPayment.setAmountPaid(payment.getAmountPaid());
        existingPayment.setPaymentNumber(payment.getPaymentNumber());
        existingPayment.setDescription(payment.getDescription());
        existingPayment.setPaymentStatus(payment.getPaymentStatus());
        existingPayment.setCreatedAt(payment.getCreatedAt());
        existingPayment.setCreatedBy(payment.getCreatedBy());
        existingPayment.setId(payment.getId());



        return paymentRepository.save(existingPayment);
    }
    public Payment getPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            return optionalPayment.get();
        } else {
            throw new EntityNotFoundException("Payment not found with id " + id);
        }
    }
    @Scheduled(cron = "0 0 0 1 * ?")
    public void autoPay() {

        List<BankAccount> bankAccounts = bankAccountRepository.findByStatusIsTrue();

        for (BankAccount bankAccount : bankAccounts) {
            User user = userRepository.findByBankAccount(bankAccount);
            Payment payment = new Payment();
            payment.setPaymentNumber("AutoPay-" + LocalDate.now().toString());
            payment.setPaymentDate(LocalDate.now());
            payment.setAmountPaid((double) user.getSalary());
            payment.setDescription("AutoPay Salary");
            payment.setPaymentStatus(PaymentStatus.PAID);
            payment.setCreatedBy("System");
            payment.setCreatedAt(LocalDateTime.now());
            payment.setBankAccount(bankAccount);
            paymentRepository.save(payment);

            Double newBalance = bankAccount.getBalance() + payment.getAmountPaid();
            bankAccount.setBalance(newBalance);
            bankAccountRepository.save(bankAccount);

        }
    }
    public void paySalary(User user, Long senderBankAccountId, Long receiverBankAccountId) {
        BankAccount senderBankAccount = bankAccountRepository.findById(senderBankAccountId).orElse(null);
        BankAccount receiverBankAccount = bankAccountRepository.findById(receiverBankAccountId).orElse(null);

        if (senderBankAccount == null || receiverBankAccount == null) {
            throw new RuntimeException("Sender or receiver bank account not found");
        }

        Double salary = Double.valueOf(user.getSalary());
        if (senderBankAccount.getBalance() < salary) {
            throw new RuntimeException("Insufficient funds");
        }

        Payment payment = new Payment();
        payment.setPaymentNumber("Salary-" + LocalDate.now().toString());
        payment.setPaymentDate(LocalDate.now());
        payment.setAmountPaid(salary);
        payment.setDescription("Salary payment");
        payment.setPaymentStatus(PaymentStatus.PAID);
        payment.setCreatedBy("System");
        payment.setCreatedAt(LocalDateTime.now());
        payment.setBankAccount(receiverBankAccount);
        paymentRepository.save(payment);

        Transactions transaction = new Transactions();
        transaction.setAmount(salary);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setDescription("Salary payment");
        transaction.setBankAccount(senderBankAccount);
        transactionRepository.save(transaction);
        Transactions transactionR = new Transactions();
        transaction.setAmount(salary);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setDescription("Salary payment For Employee");
        transaction.setBankAccount(senderBankAccount);
        transactionRepository.save(transactionR);

        Double senderNewBalance = senderBankAccount.getBalance() - salary;
        Double receiverNewBalance = receiverBankAccount.getBalance() + salary;

        senderBankAccount.setBalance(senderNewBalance);
        receiverBankAccount.setBalance(receiverNewBalance);

        bankAccountRepository.save(senderBankAccount);
        bankAccountRepository.save(receiverBankAccount);
    }
}

