package ru.alexeyshekhnov.lastproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexeyshekhnov.lastproject.entities.Task;
import ru.alexeyshekhnov.lastproject.repositories.TagRepository;
import ru.alexeyshekhnov.lastproject.repositories.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TaskRepository taskRepository;

    public Task addNewTask(Task task){
        return taskRepository.save(task);
    }
}
