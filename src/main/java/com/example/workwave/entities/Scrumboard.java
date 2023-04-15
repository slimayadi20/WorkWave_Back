package com.example.workwave.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SCRUMBOARD")
public class Scrumboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SB_ID;

    private String SB_NAME;

    private Long EMP_ID;

    // Many scrum boards can belong to one project
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    // One scrum board can have many tasks
    @OneToMany(mappedBy = "scrumboard", cascade = CascadeType.ALL)
    private List<Task> tasks;

    // getters and setters omitted for brevity

    public Long getSB_ID() {
        return SB_ID;
    }

    public void setSB_ID(Long SB_ID) {
        this.SB_ID = SB_ID;
    }

    public String getSB_NAME() {
        return SB_NAME;
    }

    public void setSB_NAME(String SB_NAME) {
        this.SB_NAME = SB_NAME;
    }

    public Long getEMP_ID() {
        return EMP_ID;
    }

    public void setEMP_ID(Long EMP_ID) {
        this.EMP_ID = EMP_ID;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Scrumboard(Long SB_ID, String SB_NAME, Long EMP_ID, Project project, List<Task> tasks) {
        this.SB_ID = SB_ID;
        this.SB_NAME = SB_NAME;
        this.EMP_ID = EMP_ID;
        this.project = project;
        this.tasks = tasks;
    }

    public Scrumboard() {
    }
}
