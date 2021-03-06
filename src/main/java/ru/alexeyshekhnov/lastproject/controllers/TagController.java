package ru.alexeyshekhnov.lastproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alexeyshekhnov.lastproject.entities.Tag;
import ru.alexeyshekhnov.lastproject.services.TagService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@CrossOrigin(origins = "https://itransitionproject.herokuapp.com/")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping
    public List<Tag> getAllTask() {
        return tagService.getAllTags();
    }

    @PostMapping("/add")
    public void addNewTags(@RequestBody Tag tag) {
        tagService.saveOrUpdate(tag);
    }

    @DeleteMapping("/delete")
    public void deleteAll() {
        tagService.deleteAll();
    }
}
