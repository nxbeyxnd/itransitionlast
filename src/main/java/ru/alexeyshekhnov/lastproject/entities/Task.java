package ru.alexeyshekhnov.lastproject.entities;


import lombok.Data;

import javax.persistence.*;
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

    @OneToMany
    @JoinColumn(name = "tag_id")
    private List<Tag> tag;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}