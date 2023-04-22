package com.example.workwave.repositories;

import com.example.workwave.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByEmail(String email);
    public boolean existsByEmail(String email);
    public List<User> findByEtat (String etat);
}
