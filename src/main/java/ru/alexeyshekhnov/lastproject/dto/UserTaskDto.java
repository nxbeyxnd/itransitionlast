package ru.alexeyshekhnov.lastproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyshekhnov.lastproject.entities.User;

@Data
@NoArgsConstructor
public class UserTaskDto {
    private Long id;
    private String username;
    private String email;

    public UserTaskDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
