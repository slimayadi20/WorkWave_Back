package com.example.workwave.repositories;

import com.example.workwave.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByEmail(String email);
    public boolean existsByEmail(String email);

}
