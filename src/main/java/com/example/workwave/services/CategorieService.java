package com.example.workwave.services;

import com.example.workwave.entities.Categorie;
import com.example.workwave.entities.holiday;
import com.example.workwave.repositories.CategorieRepository;
import com.example.workwave.repositories.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;
@Service
public class CategorieService {
    @Autowired
    CategorieRepository CategRepository;

    @Autowired
    ServletContext context;


    public List<Categorie> GetAllCateg() {
        return CategRepository.findAll();
    }

    public String addCateg(Categorie c)  {
        CategRepository.save(c);
        return "ok" ;
    }

    public String deleteCateg(Long idCateg) {
        CategRepository.deleteById(idCateg);
        return "Categorie removed !! " + idCateg;
    }


    //the update method
    public Categorie updateCateg(Categorie Categ) {


        return CategRepository.save(Categ);
    }
}
