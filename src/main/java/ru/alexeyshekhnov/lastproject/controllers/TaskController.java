package ru.alexeyshekhnov.lastproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alexeyshekhnov.lastproject.dto.TaskCreateDto;
import ru.alexeyshekhnov.lastproject.dto.TaskDto;
import ru.alexeyshekhnov.lastproject.entities.Task;
import ru.alexeyshekhnov.lastproject.services.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addNewTask(@RequestBody() TaskCreateDto newTask, @RequestHeader("Authorization") String token){
        taskService.addNewTask(newTask, token);
    }

    @GetMapping
    public List<TaskDto> getAllTask(@RequestHeader("Authorization") String token){
        return taskService.getAllTasks(token);
    }

    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable("id") long id){
        return taskService.getTaskById(id);
    }
}
