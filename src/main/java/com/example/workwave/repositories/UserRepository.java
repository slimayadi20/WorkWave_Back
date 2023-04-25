package com.example.workwave.repositories;

import com.example.workwave.entities.BankAccount;
import com.example.workwave.entities.Role;
import com.example.workwave.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    public Optional<User> findByEmail(String email);
    Page<User> findByRole(Role role, Pageable pageable);


  public User findByToken(String token);
    public boolean existsByEmail(String email);
    public User findByBankAccount(BankAccount bankAccount);
}
