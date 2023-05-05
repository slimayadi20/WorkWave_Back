package com.example.workwave.services;

import com.example.workwave.entities.Transactions;
import com.example.workwave.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BalanceService {

    @Autowired
    TransactionRepository transactionsRepository;

    public List<BalanceChange> getBalanceChange(Long accountId) {
        List<Transactions> transactions = transactionsRepository.findByBankAccount_Id(accountId);
        List<BalanceChange> balanceChanges = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        Double currentBalance = null;
        if (!transactions.isEmpty()) {
            currentBalance = transactions.get(transactions.size() - 1).getBankAccount().getBalance();
        }

        for (int i = 0; i < 30; i++) {
            LocalDate date = currentDate.minusDays(i);
            Double balance = null;
            Double percentageChange = null;

            // Find the latest transaction with a non-null amount on or before the current date
            Double previousBalance = currentBalance;
            for (Transactions transaction : transactions) {
                LocalDate transactionDate = transaction.getTransactionDate();
                if (transactionDate.isBefore(date) || transactionDate.equals(date)) {
                    if (transaction.getBankAccount() != null && transaction.getAmount() != null) {
                        previousBalance = transaction.getBankAccount().getBalance() - transaction.getAmount();
                        break;
                    }
                }
            }

            // If no transaction with a non-null amount is found, use the previous balance
            if (balanceChanges.size() > 0) {
                balance = balanceChanges.get(balanceChanges.size() - 1).getBalance();
            } else {
                balance = previousBalance;
            }

            // Calculate percentage change
            if (currentBalance != null && balance.equals(currentBalance)) {
                percentageChange = 0.0;
            } else if (previousBalance != null && previousBalance.equals(currentBalance)) {
                percentageChange = 0.0; // balance changed but resulting balance is same as previous balance
            } else if (currentBalance != null) {
                percentageChange = (balance - currentBalance) / currentBalance * 100;
            }

            balanceChanges.add(new BalanceChange(date, previousBalance, percentageChange));
        }

        // Sort the balance changes by date in descending order
        balanceChanges.sort(Comparator.comparing(BalanceChange::getDate).reversed());

        return balanceChanges;
    }



    public static class BalanceChange {
        private LocalDate date;
        private Double balance;
        private Double percentageChange;

        public BalanceChange(LocalDate date, Double balance, Double percentageChange) {
            this.date = date;
            this.balance = balance;
            this.percentageChange = percentageChange;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public Double getPercentageChange() {
            return percentageChange;
        }

        public void setPercentageChange(Double percentageChange) {
            this.percentageChange = percentageChange;
        }
    }
}
