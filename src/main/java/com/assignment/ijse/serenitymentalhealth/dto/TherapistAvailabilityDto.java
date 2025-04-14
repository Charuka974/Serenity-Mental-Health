package com.assignment.ijse.serenitymentalhealth.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TherapistAvailabilityDto {
    private String availabilityId;
    private String therapistId;
    private LocalDate availableDate;
    private LocalTime availableTime;
    private boolean isAvailable;
}
