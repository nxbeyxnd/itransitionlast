package ru.alexeyshekhnov.lastproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyshekhnov.lastproject.entities.User;

@Data
@NoArgsConstructor
public class UserTaskDto {
    private String username;

    public UserTaskDto(User user) {
        this.username = user.getUsername();
    }
}
