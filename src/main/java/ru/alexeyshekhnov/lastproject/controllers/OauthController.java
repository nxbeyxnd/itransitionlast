package ru.alexeyshekhnov.lastproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.alexeyshekhnov.lastproject.services.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@CrossOrigin(origins = "https://itransitionproject.herokuapp.com/")
public class OauthController {
    @Autowired
    private UserService userService;

    @GetMapping
    public RedirectView showUserPage(@AuthenticationPrincipal OAuth2User principal, RedirectAttributes attributes) {
        return userService.authUser(principal, attributes);
    }
}
