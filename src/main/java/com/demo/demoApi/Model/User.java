package com.demo.demoApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false, unique = true, updatable = false, columnDefinition = "int")
    private int id;

    @Column(name="username", nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(name="email", nullable = false, unique = true, columnDefinition = "varchar(100)")
    private String email;
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
