package com.assignment.ijse.serenitymentalhealth.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "therapy_program")
public class TherapyProgram {
    @Id
    @Column(length = 10)
    private String programId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal fee;
}
