package com.assignment.ijse.serenitymentalhealth.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String userId;
    private String username;
    private String password;
    private String role;
    private String email;
}
