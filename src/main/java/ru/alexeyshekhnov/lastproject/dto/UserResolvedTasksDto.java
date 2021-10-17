package ru.alexeyshekhnov.lastproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyshekhnov.lastproject.entities.Tag;
import ru.alexeyshekhnov.lastproject.entities.Task;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResolvedTasksDto {
    private Long id;
    private String desc;
    private List<Tag> tag;

    public UserResolvedTasksDto(Task task) {
        this.id = task.getId();
        this.desc = task.getDesc();
        this.tag = task.getTag();
    }
}
