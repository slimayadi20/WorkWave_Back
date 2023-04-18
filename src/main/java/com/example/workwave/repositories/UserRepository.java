package com.example.workwave.repositories;

import com.example.workwave.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    public Optional<User> findByEmail(String email);


  public User findByToken(String token);
    public boolean existsByEmail(String email);
}
