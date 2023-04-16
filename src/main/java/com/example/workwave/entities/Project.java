package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")

@Entity
@Table( name = "Project")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","User"})

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String project_name ;
    private String description ;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateEmission;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateExpiration;
    private String etat;
    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "project_user",
            joinColumns = { @JoinColumn(name = "projet_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "user_user_name", referencedColumnName = "id") })
    private Set<User> user;
    @OneToOne
    private Budget budget;
    public Long getId() {
        return id;
    }

    public Project(Long id, String project_name, String description, Date dateEmission, Date dateExpiration, String etat, Set<User> user, Budget budget) {
        this.id = id;
        this.project_name = project_name;
        this.description = description;
        this.dateEmission = dateEmission;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
        this.user = user;
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", project_name='" + project_name + '\'' +
                ", description='" + description + '\'' +
                ", dateEmission=" + dateEmission +
                ", dateExpiration=" + dateExpiration +
                ", etat='" + etat + '\'' +
                ", user=" + user +
                ", budget=" + budget +
                '}';
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Project() {
    }

    public Project(Long id, String project_name, String description,  Date dateEmission, Date dateExpiration, String etat) {
        this.id = id;
        this.project_name = project_name;
        this.description = description;
        this.dateEmission = dateEmission;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }
}
