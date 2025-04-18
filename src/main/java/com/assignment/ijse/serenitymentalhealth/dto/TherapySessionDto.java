package com.assignment.ijse.serenitymentalhealth.dto;



import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TherapySessionDto {
    private String sessionId;
    private String therapistId;
    private String patientId;
    private String therapyProgramId;
    private LocalDate sessionDate;
    private LocalTime sessionTime;
    private String status;
}
