package com.assignment.ijse.serenitymentalhealth.entity;

import jakarta.persistence.*;
import lombok.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User implements SuperEntity {
    @Id
    private String userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // "Admin" or "Receptionist"

    @Column(nullable = false)
    private String email;

//    public void setPassword(String password) {
//        this.password = new BCryptPasswordEncoder().encode(password);
//    }

}
