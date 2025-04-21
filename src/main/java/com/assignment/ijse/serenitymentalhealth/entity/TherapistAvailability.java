package com.assignment.ijse.serenitymentalhealth.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "therapist_availability")
public class TherapistAvailability implements SuperEntity {
    @Id
    private String availability_id;

    @ManyToOne
    @JoinColumn(name = "therapist_id", nullable = false)
    private Therapist therapist;

    @Column(nullable = false)
    private LocalDate available_date;

    @Column(nullable = false)
    private LocalTime start_time;

    @Column(nullable = false)
    private LocalTime end_time;

    @Column(nullable = false)
    private boolean is_available = true;

}
