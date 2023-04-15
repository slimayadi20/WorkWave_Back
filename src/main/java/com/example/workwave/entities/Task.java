package com.example.workwave.entities;

import javax.persistence.*;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TASK_ID;

    private String TASK_NAME;

    private String EMP_ID;

    // Many tasks can belong to one scrum board
    @ManyToOne
    @JoinColumn(name = "SB_ID")
    private Scrumboard scrumboard;

    // getters and setters omitted for brevity

    public Long getTASK_ID() {
        return TASK_ID;
    }

    public void setTASK_ID(Long TASK_ID) {
        this.TASK_ID = TASK_ID;
    }

    public String getTASK_NAME() {
        return TASK_NAME;
    }

    public void setTASK_NAME(String TASK_NAME) {
        this.TASK_NAME = TASK_NAME;
    }

    public String getEMP_ID() {
        return EMP_ID;
    }

    public void setEMP_ID(String EMP_ID) {
        this.EMP_ID = EMP_ID;
    }

    public Scrumboard getScrumboard() {
        return scrumboard;
    }

    public void setScrumboard(Scrumboard scrumboard) {
        this.scrumboard = scrumboard;
    }

    public Task(Long TASK_ID, String TASK_NAME, String EMP_ID, Scrumboard scrumboard) {
        this.TASK_ID = TASK_ID;
        this.TASK_NAME = TASK_NAME;
        this.EMP_ID = EMP_ID;
        this.scrumboard = scrumboard;
    }

    public Task() {
    }
}
