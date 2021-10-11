package ru.alexeyshekhnov.lastproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyshekhnov.lastproject.entities.Tag;
import ru.alexeyshekhnov.lastproject.entities.Task;

import java.util.List;

@Data
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String desc;
    private List<Tag> tag;
    private UserTaskDto userTaskDto;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.desc = task.getDesc();
        this.tag = task.getTag();
        this.userTaskDto = new UserTaskDto(task.getUser());
    }
}
