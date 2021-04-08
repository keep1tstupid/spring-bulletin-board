package com.example.bb.web;

import com.example.bb.domain.User;
import com.example.bb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //get all users
    @GetMapping("/users")
    public @ResponseBody
    List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }
}
