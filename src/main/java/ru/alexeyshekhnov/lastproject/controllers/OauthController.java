package ru.alexeyshekhnov.lastproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.alexeyshekhnov.lastproject.configurations.jwt.JwtProvider;
import ru.alexeyshekhnov.lastproject.dto.AuthResponseDto;
import ru.alexeyshekhnov.lastproject.dto.UserDto;
import ru.alexeyshekhnov.lastproject.entities.User;
import ru.alexeyshekhnov.lastproject.services.UserService;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class OauthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping
    public RedirectView showUserPage(@AuthenticationPrincipal OAuth2User principal, RedirectAttributes attributes) {
        User user = new User();
        Map<String, Object> map = principal.getAttributes();
        userService.findByEmail(map.get("email").toString()).orElseGet(() -> {
            user.setEmail(map.get("email").toString());
            user.setUsername(map.get("name").toString());
            user.setRegisterAt(LocalDateTime.now());
            user.setVisitedAt(LocalDateTime.now());
            user.setSocial(principal.getAttributes().toString());
            userService.saveOrUpdate(user);
            return user;
        });
        user.setVisitedAt(LocalDateTime.now());
        attributes.addAttribute("token", jwtProvider.generateToken(map.get("email").toString()));
        return new RedirectView("http://localhost:4200/");
    }
}
