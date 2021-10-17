package ru.alexeyshekhnov.lastproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyshekhnov.lastproject.entities.User;

@Data
@NoArgsConstructor
public class TaskUserCreatorDto {
    private String username;

    public TaskUserCreatorDto(User user) {
        this.username = user.getUsername();
    }
}
