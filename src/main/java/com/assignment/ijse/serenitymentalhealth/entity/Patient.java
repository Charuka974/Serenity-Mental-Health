package com.assignment.ijse.serenitymentalhealth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "patient")
public class Patient implements SuperEntity{
    @Id
    private String patientId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Lob
    private String address;

    @Lob
    private String medicalHistory;
}
