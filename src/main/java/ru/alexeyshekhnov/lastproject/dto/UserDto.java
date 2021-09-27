package ru.alexeyshekhnov.lastproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyshekhnov.lastproject.entities.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String social;
    private LocalDateTime registerAt;
    private LocalDateTime visitedAt;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.social = user.getSocial();
        this.registerAt = user.getRegisterAt();
        this.visitedAt = user.getVisitedAt();
    }
}
