package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "task")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    private String taskName;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "task_user",
            joinColumns = { @JoinColumn(name = "taskId", referencedColumnName = "taskId")},
            inverseJoinColumns = { @JoinColumn(name = "userName", referencedColumnName = "userName") })
    private Set<User> user;

    @ManyToOne
    @JoinColumn(name = "scrumboard_id")
    @JsonBackReference
    private ScrumBoard scrumboard;
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }


    public ScrumBoard getScrumboard() {
        return scrumboard;
    }

    public void setScrumboard(ScrumBoard scrumboard) {
        this.scrumboard = scrumboard;
    }

    public Task(Long taskId, String taskName, Set<User> user) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.user = user;
    }

    public Task() {
    }
}



