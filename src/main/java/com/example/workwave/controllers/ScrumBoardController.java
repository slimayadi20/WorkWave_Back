package com.example.workwave.controllers;

import com.example.workwave.entities.Scrumboard;
import com.example.workwave.services.ScrumBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScrumBoardController {
    @Autowired
    private ScrumBoardServiceImpl  scrumboardService;

    @GetMapping("/ScrumBoard")
    public List<Scrumboard> getAllScrumboards() {
        return scrumboardService.getAllScrumboards();
    }

    @GetMapping("/ScrumBoard/{id}")
    public Scrumboard getScrumboardById(@PathVariable Long id) {
        return scrumboardService.getScrumboardById(id);
    }

    @PostMapping("/addScrumBoard")
    public Scrumboard createScrumboard(@RequestBody Scrumboard scrumboard) {
        return scrumboardService.createScrumboard(scrumboard);
    }

    @PutMapping("/updateScrumBoard")
    public Scrumboard updateScrumboard(@PathVariable Long id, @RequestBody Scrumboard scrumboard) {
        return scrumboardService.updateScrumboard(id, scrumboard);
    }

    @DeleteMapping("/deleteScrumBoard/{id}")
    public void deleteScrumboard(@PathVariable Long id) {
        scrumboardService.deleteScrumboard(id);
    }
}
