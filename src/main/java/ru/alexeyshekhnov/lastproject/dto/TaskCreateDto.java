package ru.alexeyshekhnov.lastproject.dto;

import ru.alexeyshekhnov.lastproject.entities.Tag;

import java.util.List;

public class TaskCreateDto {
    public String desc;
    public String answer;
    public List<Tag> tag;

    public TaskCreateDto(String desc, String answer, List<Tag> tag) {
        this.desc = desc;
        this.answer = answer;
        this.tag = tag;
    }
}
