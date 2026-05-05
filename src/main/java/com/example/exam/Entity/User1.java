package com.example.exam.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User1 {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;
}