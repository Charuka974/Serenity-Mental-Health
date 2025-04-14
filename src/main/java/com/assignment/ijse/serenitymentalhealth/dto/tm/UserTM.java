package com.assignment.ijse.serenitymentalhealth.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTM {
    private String userId;
    private String username;
    private String password;
    private String role;
    private String email;
}
