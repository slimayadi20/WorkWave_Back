package com.example.workwave.services;

import com.example.workwave.entities.BankAccount;
import com.example.workwave.entities.Payment;
import com.example.workwave.repositories.BudgetRepository;
import com.example.workwave.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;
@Service
public class PaymentServiceImpl {
    @Autowired
    PaymentRepository paymentRepository;

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
        existingPayment.setStatus(payment.getStatus());
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
    public List<Payment> GetPaymentsByBankAccount(BankAccount bankAccount) {
        return paymentRepository.findByBankAccount(bankAccount);
    }

}

