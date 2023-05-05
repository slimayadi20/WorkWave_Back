package com.example.workwave.repositories;
import com.example.workwave.entities.BankAccount;
import com.example.workwave.entities.Budget;
import com.example.workwave.entities.Transactions;
import com.example.workwave.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface TransactionRepository extends JpaRepository<Transactions,Long> {

        List<Transactions> findByBankAccount_Id(Long id);

        @Query("SELECT ABS(AVG(t.amount)) FROM Transactions t WHERE t.bankAccount.id = :bankAccountId")
        Double getAverageTransactionAmountByBankAccountId(@Param("bankAccountId") Long bankAccountId);
        @Query("SELECT COUNT(t) FROM Transactions t WHERE MONTH(t.transactionDate) = MONTH(CURRENT_DATE()) AND YEAR(t.transactionDate) = YEAR(CURRENT_DATE()) AND t.bankAccount.id = :bankAccountId")
        Long countTransactionsByMonthAndBankAccountId(@Param("bankAccountId") Long bankAccountId);
        @Query("SELECT COUNT(t) FROM Transactions t WHERE t.transactionDate = current_date() AND t.bankAccount.id = :bankAccountId")
        Long getTransactionCountByBankAccountIdToday(@Param("bankAccountId") Long bankAccountId);

}