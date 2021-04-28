package com.example.bb.web;

import com.example.bb.domain.Role;
import com.example.bb.domain.User;
import com.example.bb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    // get all users
    @GetMapping("/users")
    public @ResponseBody
    List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }

    // get all roles
    @GetMapping("/roles")
    public @ResponseBody
    List<Role> allRoles() {
        Role[] roles = Role.values();
        return Arrays.asList(roles);
    }

    // add new user
    @PostMapping("/users")
    User addUser(@RequestBody User newUser) {
        String encoded = new BCryptPasswordEncoder().encode(newUser.getPassword());
        User userToSave = new User(newUser.getUsername(), newUser.getRole(), newUser.getEmail(), encoded);
        return userRepository.save(userToSave);
    }
}