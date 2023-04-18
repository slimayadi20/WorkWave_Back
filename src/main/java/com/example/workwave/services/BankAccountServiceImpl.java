package com.example.workwave.services;

import com.example.workwave.entities.BankAccount;
import com.example.workwave.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class BankAccountServiceImpl {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    ServletContext context;

    public List<BankAccount> GetAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public String addBankAccount( BankAccount b)  {
        bankAccountRepository.save(b);
        return "ok" ;
    }

    public String deleteBankAccount(Long idBankAccount) {
        bankAccountRepository.deleteById(idBankAccount);
        return "BankAccount removed !! " + idBankAccount;
    }


    //the update method
    public BankAccount updateBankAccount(BankAccount bankAccount) {
        BankAccount existingBankAccount = bankAccountRepository.findById(bankAccount.getId()).orElse(null);
        existingBankAccount.setAccountName(bankAccount.getAccountName());
        existingBankAccount.setAccountNumber(bankAccount.getAccountNumber());
        existingBankAccount.setBankName(bankAccount.getBankName());
        existingBankAccount.setBalance(bankAccount.getBalance());
        existingBankAccount.setUser(bankAccount.getUser());
        existingBankAccount.setPayments(bankAccount.getPayments());
        existingBankAccount.setInvoices(bankAccount.getInvoices());
        existingBankAccount.setStatus(bankAccount.getStatus());
        existingBankAccount.setLimitAmount(bankAccount.getLimitAmount());
        existingBankAccount.setTransactions(bankAccount.getTransactions());
        existingBankAccount.setId(existingBankAccount.getId());



        return bankAccountRepository.save(existingBankAccount);
    }
    public BankAccount getBankAccountById(Long id) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(id);
        if (optionalBankAccount.isPresent()) {
            return optionalBankAccount.get();
        } else {
            throw new EntityNotFoundException("Bank Account not found with id " + id);
        }
    }

}
