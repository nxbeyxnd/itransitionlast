package ru.alexeyshekhnov.lastproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import ru.alexeyshekhnov.lastproject.configurations.jwt.JwtProvider;
import ru.alexeyshekhnov.lastproject.dto.AuthResponseDto;
import ru.alexeyshekhnov.lastproject.dto.UserDto;
import ru.alexeyshekhnov.lastproject.entities.User;
import ru.alexeyshekhnov.lastproject.services.UserService;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto showUserPage(@AuthenticationPrincipal OAuth2User principal) {
        User user = new User();
        Map<String, Object> map = principal.getAttributes();
        userService.findByEmail(map.get("email").toString()).orElseGet(()->{
            user.setEmail(map.get("email").toString());
            user.setUsername(map.get("name").toString());
            user.setRegisterAt(LocalDateTime.now());
            user.setVisitedAt(LocalDateTime.now());
            user.setSocial(principal.getAttributes().toString());
            userService.saveOrUpdate(user);
            return user;
        });
        user.setVisitedAt(LocalDateTime.now());
        return new UserDto(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/help")
    public User showUser(@AuthenticationPrincipal OAuth2User principal){
        Map<String, Object> map = principal.getAttributes();
        return userService.findUserByEmail(map.get("email").toString());
    }

    @GetMapping("/log")
    public AuthResponseDto auth(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> map = principal.getAttributes();
        User user = userService.findUserByEmail(map.get("email").toString());
        String token = jwtProvider.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }
}
