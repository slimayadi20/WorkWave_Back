package com.example.workwave.services;

import com.example.workwave.entities.Cours;
import com.example.workwave.repositories.CoursRepository;
import com.example.workwave.repositories.FormationRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CoursService {
    @Autowired
    CoursRepository courRepo;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    ServletContext context;

    public List<Cours> ShowCours() {
        return courRepo.findAll();
    }

    public ResponseEntity<String> addCours(Cours c) {
        try {
            courRepo.save(c);
            return new ResponseEntity<>("{\"message\": \"Formation saved successfully.\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Error saving formation: " + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteCours(Long idCours) {
        courRepo.deleteById(idCours);
        return new ResponseEntity<>("{\"message\": \"Formation deleted successfully.\"}", HttpStatus.OK);
    }

    public Cours updateCours(Cours c) {

        return courRepo.save(c);
    }

    public Optional<Cours> getForm(Long id) {
        return courRepo.findById(id);
    }

    public Optional<Cours> getCours(Long idFormation) {

        return courRepo.findByFormation(formationRepository.findById(idFormation).get());
    }


    public byte[] getPhoto(Long id) throws Exception {
        Cours c = courRepo.findById(id).get();
        String filePath = "src/main/webapp/Images/" + c.getImage1();
        return Files.readAllBytes(Paths.get(filePath));
    }
}

