package ru.alexeyshekhnov.lastproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alexeyshekhnov.lastproject.configurations.jwt.JwtProvider;
import ru.alexeyshekhnov.lastproject.dto.UserpageDto;
import ru.alexeyshekhnov.lastproject.entities.User;
import ru.alexeyshekhnov.lastproject.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserPageController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/whoami")
    public User getAllInfo(@RequestHeader("Authorization") String token){
        return userService.findUserByEmail(jwtProvider.getLoginFromToken(token));
    }

    @GetMapping("/{id}")
    public UserpageDto getUserpage(@PathVariable Long id){
        return userService.findUserById(id);
    }

}
