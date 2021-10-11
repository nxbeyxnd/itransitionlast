package ru.alexeyshekhnov.lastproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexeyshekhnov.lastproject.dto.TaskDto;
import ru.alexeyshekhnov.lastproject.dto.UserpageDto;
import ru.alexeyshekhnov.lastproject.entities.Role;
import ru.alexeyshekhnov.lastproject.entities.Task;
import ru.alexeyshekhnov.lastproject.entities.User;
import ru.alexeyshekhnov.lastproject.repositories.RoleRepository;
import ru.alexeyshekhnov.lastproject.repositories.TaskRepository;
import ru.alexeyshekhnov.lastproject.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User saveOrUpdate(User user){
        Role role = roleRepository.findByName("ROLE_USER");
        if(user.getSocial().contains("google"))
            user.setSocial("Google");
        else if(user.getSocial().contains("github"))
            user.setSocial("GitHub");
        else if(user.getSocial().contains("facebook"))
            user.setSocial("Facebook");
        user.setRole(role);
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public UserpageDto findUserById(Long id){
        List<TaskDto> tasks = new ArrayList<>();
        for (Task t: taskRepository.findAllByUser_Id(id)){
            tasks.add(new TaskDto(t));
        }
        return new UserpageDto(userRepository.getById(id),tasks);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
