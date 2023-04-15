package com.example.workwave.controllers;

import com.example.workwave.entities.Project;
import com.example.workwave.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectService;

    @GetMapping("/project")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("/addProject")
    public Project createProject(@RequestBody Project project) {
        System.out.println("project");
        System.out.println(project.getPROJECT_NAME());
        return projectService.createProject(project);
    }

    @PutMapping("/updateProject/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        System.out.println(project.getEMP_ID());
        System.out.println(project.getPROJECT_NAME());

        System.out.println(id);
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/deleteProject/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
