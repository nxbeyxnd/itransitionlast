package ru.alexeyshekhnov.lastproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexeyshekhnov.lastproject.dto.TaskDto;
import ru.alexeyshekhnov.lastproject.entities.Task;
import ru.alexeyshekhnov.lastproject.repositories.TagRepository;
import ru.alexeyshekhnov.lastproject.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TaskRepository taskRepository;

    public Task addNewTask(Task task) {
        return taskRepository.save(task);
    }

    public List<TaskDto> getAllTasks() {
        List<TaskDto> tasks = new ArrayList<>();
        for (Task t : taskRepository.findAll()) {
            tasks.add(new TaskDto(t));
        }
        return tasks;
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}
