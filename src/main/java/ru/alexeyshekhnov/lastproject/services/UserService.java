package ru.alexeyshekhnov.lastproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.alexeyshekhnov.lastproject.configurations.jwt.JwtProvider;
import ru.alexeyshekhnov.lastproject.dto.TaskDto;
import ru.alexeyshekhnov.lastproject.dto.UserResolvedTasksDto;
import ru.alexeyshekhnov.lastproject.dto.UserpageDto;
import ru.alexeyshekhnov.lastproject.entities.Role;
import ru.alexeyshekhnov.lastproject.entities.Task;
import ru.alexeyshekhnov.lastproject.entities.User;
import ru.alexeyshekhnov.lastproject.repositories.RoleRepository;
import ru.alexeyshekhnov.lastproject.repositories.TaskRepository;
import ru.alexeyshekhnov.lastproject.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private JwtProvider jwtProvider;

    public User saveOrUpdate(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        if (user.getSocial().contains("google"))
            user.setSocial("Google");
        else if (user.getSocial().contains("github"))
            user.setSocial("GitHub");
        else if (user.getSocial().contains("facebook"))
            user.setSocial("Facebook");
        user.setRole(role);
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserpageDto findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return new UserpageDto(user,getAllTasksByUSerId(user.getId()),getAllResolvedTaskByUserId(user.getId()));
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public UserpageDto findUserById(Long id) {
        return new UserpageDto(userRepository.getById(id), getAllTasksByUSerId(id), getAllResolvedTaskByUserId(id));
    }

    public List<UserResolvedTasksDto> getAllResolvedTaskByUserId(Long id){
        User user = userRepository.getUserById(id);
        List<UserResolvedTasksDto> resolvedTasks = new ArrayList<>();
        for (Task t : user.getTask()) {
            resolvedTasks.add(new UserResolvedTasksDto(t));
        }
        return resolvedTasks;
    }

    public List<TaskDto> getAllTasksByUSerId(Long id){
        List<TaskDto> tasks = new ArrayList<>();
        for (Task t : taskRepository.findAllByUser_Id(id)) {
            tasks.add(new TaskDto(t));
        }
        return tasks;
    }

    public RedirectView authUser(OAuth2User principal, RedirectAttributes attributes) {
        User user = new User();
        Map<String, Object> map = principal.getAttributes();
        findByEmail(map.get("email").toString()).orElseGet(() -> {
            user.setEmail(map.get("email").toString());
            user.setUsername(map.get("name").toString());
            user.setRegisterAt(LocalDateTime.now());
            user.setVisitedAt(LocalDateTime.now());
            user.setSocial(principal.getAttributes().toString());
            saveOrUpdate(user);
            return user;
        });
        user.setVisitedAt(LocalDateTime.now());
        attributes.addAttribute("token", jwtProvider.generateToken(map.get("email").toString()));
        return new RedirectView("http://localhost:4200/");
    }

    public void getResolve(Long taskId, String token) {
        User user = getUserByEmail(jwtProvider.getLoginFromToken(token));
        List<Task> tasks = user.getTask();
        tasks.add(taskService.getTaskById(taskId));
        user.setTask(tasks);
        userRepository.save(user);
    }
}
