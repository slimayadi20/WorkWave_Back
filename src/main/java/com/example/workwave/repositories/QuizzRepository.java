package com.example.workwave.repositories;
import com.example.workwave.entities.Cours;
import com.example.workwave.entities.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzRepository extends JpaRepository<Quizz,Long>{
}
