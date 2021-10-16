package ru.alexeyshekhnov.lastproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexeyshekhnov.lastproject.configurations.jwt.JwtProvider;
import ru.alexeyshekhnov.lastproject.dto.TaskCreateDto;
import ru.alexeyshekhnov.lastproject.dto.TaskDto;
import ru.alexeyshekhnov.lastproject.entities.Tag;
import ru.alexeyshekhnov.lastproject.entities.Task;
import ru.alexeyshekhnov.lastproject.entities.User;
import ru.alexeyshekhnov.lastproject.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Transactional
    public void addNewTask(TaskCreateDto newTask, String token) {
        Task task = new Task();
        List<Tag> tags = new ArrayList<>();
        User user;
        if (jwtProvider.validateToken(token)) {
            user = userService.findUserByEmail(jwtProvider.getLoginFromToken(token));
            task.setDesc(newTask.desc);
            task.setAnswer(newTask.answer);
            for (Tag t : newTask.tag) {
                tagService.saveOrUpdate(t);
                tags.add(tagService.getTagByName(t.getName()));
            }
            task.setTag(tags);
            task.setUser(user);
            taskRepository.save(task);
        }
    }

    public List<TaskDto> getAllTasks(String token) {
        jwtProvider.getLoginFromToken(token);
        List<TaskDto> tasks = new ArrayList<>();
        for (Task t : taskRepository.findAll()) {
            tasks.add(new TaskDto(t));
        }
        return tasks;
    }

    public Optional<Task> getTaskById(Long id) {
        return taskService.getTaskById(id);
    }
}
