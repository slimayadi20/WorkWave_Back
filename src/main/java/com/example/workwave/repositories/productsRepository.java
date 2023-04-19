package com.example.workwave.repositories;

import com.example.workwave.entities.products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  productsRepository extends JpaRepository <products, Long> {


}
