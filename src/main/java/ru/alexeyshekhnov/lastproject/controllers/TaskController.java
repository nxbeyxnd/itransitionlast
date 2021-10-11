package ru.alexeyshekhnov.lastproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alexeyshekhnov.lastproject.configurations.jwt.JwtProvider;
import ru.alexeyshekhnov.lastproject.dto.TaskDto;
import ru.alexeyshekhnov.lastproject.dto.UserTaskDto;
import ru.alexeyshekhnov.lastproject.entities.Tag;
import ru.alexeyshekhnov.lastproject.entities.Task;
import ru.alexeyshekhnov.lastproject.entities.User;
import ru.alexeyshekhnov.lastproject.services.TaskService;
import ru.alexeyshekhnov.lastproject.services.UserService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    //TODO Change THAT to POSTRequest
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addNewTask(@RequestBody() String taskParams, @RequestHeader("Authorization") String token){
        Task task = new Task();
        User user;
        if (jwtProvider.validateToken(token)){
            user = userService.findUserByEmail(jwtProvider.getLoginFromToken(token));
            task.setDesc("TEST");
            task.setAnswer("TEST");
            List<Tag> tags = new ArrayList<>();
            task.setTag(tags);
            task.setUser(user);
            taskService.addNewTask(task);
        }
    }

    @GetMapping
    public List<TaskDto> getAllTask(@RequestHeader("Authorization") String token){
        jwtProvider.getLoginFromToken(token);
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable("id") long id){
        return taskService.getTaskById(id);
    }
}
