package ru.alexeyshekhnov.lastproject.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "social")
    private String social;

    @Column(name = "register_at")
    private LocalDateTime registerAt;

    @Column(name = "visited_at")
    private LocalDateTime visitedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
