package com.example.workwave.services;

import com.example.workwave.entities.Categorie;
import com.example.workwave.entities.Formation;
import com.example.workwave.repositories.CategorieRepository;
import com.example.workwave.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;

@Service
public class FormationService {
    @Autowired
    FormationRepository FormRepository;

    @Autowired
    ServletContext context;


    public List<Formation> ShowForm() {
        return FormRepository.findAll();
    }

    public String addForm(Formation f)  {
        FormRepository.save(f);
        return "ok" ;
    }

    public String deleteForm(Long idFormation) {
        FormRepository.deleteById(idFormation);
        return "Categorie removed !! " + idFormation;
    }


    //the update method
    public Formation  updateForm(Formation f) {

        return FormRepository.save(f);
    }


}
