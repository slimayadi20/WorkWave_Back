package com.example.workwave.controllers;

import com.example.workwave.entities.Cours;
import com.example.workwave.repositories.CoursRepository;
import com.example.workwave.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CoursController {
    @Autowired
    CoursService coursService;

    @Autowired
    CoursRepository coursRepo;


    @PostMapping("/addCours")
    public ResponseEntity<String> addCours(@RequestBody Cours payload) {
        return coursService.addCours(payload);
    }

    @DeleteMapping("/deleteCours/{id}")
    public ResponseEntity<String> deleteCours(@PathVariable long id) {
        return coursService.deleteCours(id);
    }

    @PutMapping("/updateCours")
    public Cours updateCours(@RequestBody Cours c) {
        return coursService.updateCours(c);
    }

    @GetMapping("/ShowCours")//affichage+pagination
    public List<Cours> ShowCours() {
        return coursService.ShowCours();
    }

    @GetMapping("/ShowCours/{idCours}")
    public Optional<Cours> getCours(@PathVariable Long idCours) {
        return coursService.getForm(idCours);
    }
   @GetMapping("/CoursFormation/{idformation}")
    public Optional<Cours> CoursFormation(@PathVariable Long idformation) {
        return coursService.getCours(idformation);
    }

    @GetMapping(path = "/imgcours/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        return coursService.getPhoto(id);
    }
}
