package com.example.workwave.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity

public class Historique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistorique")
    private Long idHistorique;

    @OneToOne
    private User user;
    @OneToOne
    private Formation formation;
    private int score;
    private int avancement;
    private String etat;

    public Historique() {
    }

    public Long getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(Long idHistorique) {
        this.idHistorique = idHistorique;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAvancement() {
        return avancement;
    }

    public void setAvancement(int avancement) {
        this.avancement = avancement;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
