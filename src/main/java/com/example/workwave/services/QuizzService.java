package com.example.workwave.services;

import com.example.workwave.entities.Cours;
import com.example.workwave.entities.Quizz;
import com.example.workwave.repositories.CoursRepository;
import com.example.workwave.repositories.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizzService {

    @Autowired
    static
    QuizzRepository quizzRepo;

    public static List<Quizz> ShowQuizz() {
        return quizzRepo.findAll();
    }

    public ResponseEntity<String> addQuizz(Quizz q) {
        try {
            quizzRepo.save(q);
            return new ResponseEntity<>("{\"message\": \"Formation saved successfully.\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Error saving formation: " + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteQuizz(Long idQuizz) {
        quizzRepo.deleteById(idQuizz);
        return new ResponseEntity<>("{\"message\": \"Formation deleted successfully.\"}", HttpStatus.OK);
    }

    public Quizz updateQuizz(Quizz q) {

        return quizzRepo.save(q);
    }

    public Optional<Quizz> getQuizz(Long id) {
        return quizzRepo.findById(id);
    }
}
