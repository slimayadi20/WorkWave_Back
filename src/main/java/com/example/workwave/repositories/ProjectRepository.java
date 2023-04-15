package com.example.workwave.repositories;

import com.example.workwave.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
