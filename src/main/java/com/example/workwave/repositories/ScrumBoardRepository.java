package com.example.workwave.repositories;

import com.example.workwave.entities.Scrumboard;
import com.example.workwave.entities.holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrumBoardRepository extends JpaRepository<Scrumboard,Long> {
}
