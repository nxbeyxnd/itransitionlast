package ru.alexeyshekhnov.lastproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyshekhnov.lastproject.entities.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserpageDto {
    private Long id;
    private String username;
    private LocalDateTime visitedAt;
    private List<TaskDto> createdTasks;
    private List<UserResolvedTasksDto> resolvedTasks;

    public UserpageDto(User user, List<TaskDto> task, List<UserResolvedTasksDto> resolvedTasks) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.visitedAt = user.getVisitedAt();
        this.createdTasks = task;
        this.resolvedTasks = resolvedTasks;
    }
}
