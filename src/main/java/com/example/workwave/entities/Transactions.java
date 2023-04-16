package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.*;
import com.example.workwave.entities.Budget;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table( name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Date transactionDate;

    private String description;

    @ManyToOne
    private BankAccount bankAccount;

}
