package com.example.workwave.controllers;

import com.example.workwave.entities.Categorie;
import com.example.workwave.services.CategorieService;
import com.example.workwave.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CategorieController {
    @Autowired
    CategorieService CategService;

    @Autowired
    CategorieRepository CategRepository;

    @PostMapping("/addCateg")
    public String addCateg(@RequestBody Map<String, Object> payload) {

        Categorie Categ = new Categorie();
        Categ.setNom((String) payload.get("nom"));
        return CategService.addCateg(Categ);
    }
    @DeleteMapping("/deleteCateg/{id}")
    public String deleteCateg(@PathVariable long id) {
        return CategService.deleteCateg(id);
    }

    @PutMapping("/updateCateg")
    public Categorie updateCateg(@RequestBody Categorie categ) {
        return CategService.updateCateg(categ);
    }

    @GetMapping("/Categ")//affichage+pagination
    public List<Categorie> show() {
        return CategService.GetAllCateg();
    }

}
