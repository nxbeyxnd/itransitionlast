package ru.alexeyshekhnov.lastproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alexeyshekhnov.lastproject.configurations.jwt.JwtProvider;
import ru.alexeyshekhnov.lastproject.entities.Task;
import ru.alexeyshekhnov.lastproject.entities.User;
import ru.alexeyshekhnov.lastproject.services.TaskService;
import ru.alexeyshekhnov.lastproject.services.UserService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/add")
    public String addNewTask(@RequestParam("a") String token){
        Task task = new Task();
        User user;
        if (jwtProvider.validateToken(token)){
            user = userService.findUserByEmail(jwtProvider.getLoginFromToken(token));
            task.setDesc("TEST");
            task.setAnswer("TEST");
            task.setUser(user);
            taskService.addNewTask(task);
            return "OK";
        }
        return "BAD";
    }
}
