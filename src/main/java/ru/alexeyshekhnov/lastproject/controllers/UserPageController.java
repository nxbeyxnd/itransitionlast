package ru.alexeyshekhnov.lastproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alexeyshekhnov.lastproject.configurations.jwt.JwtProvider;
import ru.alexeyshekhnov.lastproject.dto.UserpageDto;
import ru.alexeyshekhnov.lastproject.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "https://itransitionproject.herokuapp.com/")
public class UserPageController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/me")
    public UserpageDto getAllInfo(@RequestHeader("Authorization") String token){
        return userService.findUserByEmail(jwtProvider.getLoginFromToken(token));
    }

    @GetMapping("/{id}")
    public UserpageDto findAll(@PathVariable Long id){
        return userService.findUserById(id);
    }
}
