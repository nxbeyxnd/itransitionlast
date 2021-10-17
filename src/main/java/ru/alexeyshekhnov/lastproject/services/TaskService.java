package ru.alexeyshekhnov.lastproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexeyshekhnov.lastproject.configurations.jwt.JwtProvider;
import ru.alexeyshekhnov.lastproject.dto.ResolveRequestDto;
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
    private TaskRepository taskRepository;

    @Autowired
    private JwtProvider jwtProvider;

    public void addNewTask(TaskCreateDto newTask, String token) {
        Task task = new Task();
        List<Tag> tags = new ArrayList<>();
        User user;
        if (jwtProvider.validateToken(token)) {
            user = userService.getUserByEmail(jwtProvider.getLoginFromToken(token));
            task.setDesc(newTask.desc);
            task.setAnswer(newTask.answer);
            for (Tag t : newTask.tag) {
                tagService.saveOrUpdate(t);
                if (!tags.contains(tagService.getTagByName(t.getName()))) {
                    tags.add(tagService.getTagByName(t.getName()));
                }
            }
            task.setTag(tags);
            task.setUser(user);
            taskRepository.save(task);
        }
    }

    public List<TaskDto> getAllTasks() {
        List<TaskDto> tasks = new ArrayList<>();
        for (Task t : taskRepository.findAll()) {
            tasks.add(new TaskDto(t));
        }
        return tasks;
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    public String getResolve(ResolveRequestDto userAnswer, String token) {
        Optional<Task> task = findTaskById(userAnswer.getTaskId());
        if (task.get().getAnswer().equals(userAnswer.getAnswer())) {
            userService.getResolve(userAnswer.getTaskId(), token);
            return "True";
        } else return "false";
    }
}
