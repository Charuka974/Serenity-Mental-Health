package com.assignment.ijse.serenitymentalhealth.dto;

import com.assignment.ijse.serenitymentalhealth.entity.Patient;
import com.assignment.ijse.serenitymentalhealth.entity.TherapyProgram;
import com.assignment.ijse.serenitymentalhealth.entity.TherapySession;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PaymentDto {
    private String paymentId;
    private Patient patient;
    private TherapyProgram therapyProgram;
    private TherapySession therapySession;  // Nullable for upfront payments
    private BigDecimal amount;
    private LocalDate paymentDate;
}
