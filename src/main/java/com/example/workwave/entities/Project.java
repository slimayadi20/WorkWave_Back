package com.example.workwave.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PROJECT_ID;

    private String PROJECT_NAME;

    private Long EMP_ID;

    // One project can have many scrum boards
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Scrumboard> scrumboards;

    // getters and setters omitted for brevity

    public Long getPROJECT_ID() {
        return PROJECT_ID;
    }

    public void setPROJECT_ID(Long PROJECT_ID) {
        this.PROJECT_ID = PROJECT_ID;
    }

    public String getPROJECT_NAME() {
        return PROJECT_NAME;
    }

    public void setPROJECT_NAME(String PROJECT_NAME) {
        this.PROJECT_NAME = PROJECT_NAME;
    }

    public Long getEMP_ID() {
        return EMP_ID;
    }

    public void setEMP_ID(Long EMP_ID) {
        this.EMP_ID = EMP_ID;
    }

    public List<Scrumboard> getScrumboards() {
        return scrumboards;
    }

    public void setScrumboards(List<Scrumboard> scrumboards) {
        this.scrumboards = scrumboards;
    }

    public Project(Long PROJECT_ID, String PROJECT_NAME, Long EMP_ID, List<Scrumboard> scrumboards) {
        this.PROJECT_ID = PROJECT_ID;
        this.PROJECT_NAME = PROJECT_NAME;
        this.EMP_ID = EMP_ID;
        this.scrumboards = scrumboards;
    }

    public Project() {
    }
}


