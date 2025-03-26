package com.assignment.ijse.serenitymentalhealth.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TherapyProgramDto {
    private String programId;
    private String name;
    private String duration;
    private BigDecimal fee;
    private String description;
}
