package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.*;
import com.example.workwave.entities.Budget;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table( name = "BankAccount")

public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountName;

    private String accountNumber;

    private Double balance;

    private Double limit;

    private String bankName;

    private Boolean status;

    @OneToOne(mappedBy = "bankAccount")
    private Budget budget;

    @OneToMany(mappedBy = "bankAccount")
    private List<Invoices> invoices;

    @OneToMany(mappedBy = "bankAccount")
    private List<Payment> payments;

    @OneToOne(mappedBy = "bankAccount")
    private User user;

    @OneToMany(mappedBy = "bankAccount")
    private List<Transactions> transactions;
    public BankAccount() {
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<Invoices> getInvoices() {
        return invoices;
    }



    public void setInvoices(List<Invoices> invoices) {
        this.invoices = invoices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BankAccount(Long id, String accountName, String accountNumber, Double balance, Double limit, String bankName, Boolean status, Budget budget, List<Invoices> invoices, List<Payment> payments, User user, List<Transactions> transactions) {
        this.id = id;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.limit = limit;
        this.bankName = bankName;
        this.status = status;
        this.budget = budget;
        this.invoices = invoices;
        this.payments = payments;
        this.user = user;
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", limit=" + limit +
                ", bankName='" + bankName + '\'' +
                ", status=" + status +
                ", budget=" + budget +
                ", invoices=" + invoices +
                ", payments=" + payments +
                ", user=" + user +
                ", transactions=" + transactions +
                '}';
    }
}
