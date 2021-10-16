package ru.alexeyshekhnov.lastproject.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String desc;

    @Column(name = "answer")
    private String answer;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinTable(name = "tasks_tag",
            joinColumns = @JoinColumn(name = "id_task"),
            inverseJoinColumns = @JoinColumn(name = "id_tag"))
    private List<Tag> tag = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}