package ru.alexeyshekhnov.lastproject.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}