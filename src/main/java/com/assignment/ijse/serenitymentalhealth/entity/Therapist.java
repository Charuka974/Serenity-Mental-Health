package com.assignment.ijse.serenitymentalhealth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "therapist")
public class Therapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int therapistId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String specialization;
}
