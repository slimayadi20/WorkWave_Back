package com.example.workwave.services;

import com.example.workwave.entities.Project;
import com.example.workwave.entities.Scrumboard;
import com.example.workwave.repositories.ScrumBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ScrumBoardServiceImpl {
    @Autowired
    private ScrumBoardRepository scrumboardRepository;

    public List<Scrumboard> getAllScrumboards() {
        return scrumboardRepository.findAll();
    }

    public Scrumboard getScrumboardById(Long id) {
        Optional<Scrumboard> optionalScrumboard = scrumboardRepository.findById(id);
        if (optionalScrumboard.isPresent()) {
            return optionalScrumboard.get();
        } else {
            throw new EntityNotFoundException("Scrumboard not found with id " + id);
        }
    }

    public Scrumboard createScrumboard(Scrumboard scrumboard) {
        return scrumboardRepository.save(scrumboard);
    }

    public Scrumboard updateScrumboard(Long id, Scrumboard scrumboard) {
       /* Optional<Scrumboard> optionalScrumboard = scrumboardRepository.findById(id);
        if (optionalScrumboard.isPresent()) {
            Scrumboard existingScrumboard = optionalScrumboard.get();
            existingScrumboard.setSbName(scrumboard.getSbName());
            existingScrumboard.setEmpId(scrumboard.getEmpId());
            return scrumboardRepository.save(existingScrumboard);
        } else {
            throw new EntityNotFoundException("Scrumboard not found with id " + id);
        }

        */
        Scrumboard existingScrumBoard = scrumboardRepository.findById(scrumboard.getSB_ID()).orElse(null);
        existingScrumBoard.setSB_ID(scrumboard.getSB_ID());
        existingScrumBoard.setSB_NAME(scrumboard.getSB_NAME());
        existingScrumBoard.setEMP_ID(scrumboard.getEMP_ID());
        existingScrumBoard.setTasks(scrumboard.getTasks());


        return scrumboardRepository.save(existingScrumBoard);
    }

    public void deleteScrumboard(Long id) {
        Optional<Scrumboard> optionalScrumboard = scrumboardRepository.findById(id);
        if (optionalScrumboard.isPresent()) {
            scrumboardRepository.delete(optionalScrumboard.get());
        } else {
            throw new EntityNotFoundException("Scrumboard not found with id " + id);
        }
    }
}
