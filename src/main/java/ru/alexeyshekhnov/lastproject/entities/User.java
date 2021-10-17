package ru.alexeyshekhnov.lastproject.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinTable(name = "users_solved",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_task"))
    private List<Task> Task = new ArrayList<>();
}
