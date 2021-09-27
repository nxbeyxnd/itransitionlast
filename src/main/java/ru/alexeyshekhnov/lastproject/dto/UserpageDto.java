package ru.alexeyshekhnov.lastproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyshekhnov.lastproject.entities.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserpageDto {
    private Long id;
    private String username;
    private LocalDateTime visitedAt;

    public UserpageDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.visitedAt = user.getVisitedAt();
    }
}
