package com.example.workwave.services;


import com.example.workwave.entities.Project;
import com.example.workwave.entities.holiday;
import com.example.workwave.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            throw new EntityNotFoundException("Project not found with id " + id);
        }
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
    /*    Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            existingProject.setProjectName(project.getProjectName());
            existingProject.setEmpId(project.getEmpId());
            return projectRepository.save(existingProject);
        } else {
            throw new EntityNotFoundException("Project not found with id " + id);
        }
        */
        Project existingProject = projectRepository.findById(project.getPROJECT_ID()).orElse(null);
        existingProject.setPROJECT_NAME(project.getPROJECT_NAME());
        existingProject.setEMP_ID(project.getEMP_ID());


        System.out.println(project.getPROJECT_NAME());
        System.out.println(project.getEMP_ID());
        System.out.println("existingProject");
        System.out.println(existingProject);
        existingProject.setScrumboards(project.getScrumboards());

        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            projectRepository.delete(optionalProject.get());
        } else {
            throw new EntityNotFoundException("Project not found with id " + id);
        }
    }
}
