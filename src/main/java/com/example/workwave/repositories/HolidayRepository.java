package com.example.workwave.repositories;
import com.example.workwave.entities.User;
import com.example.workwave.entities.holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface HolidayRepository  extends JpaRepository<holiday,Long> {
    List<holiday> findByUser(User user);


}
