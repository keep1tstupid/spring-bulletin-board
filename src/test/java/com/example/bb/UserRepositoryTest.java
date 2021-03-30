package com.example.bb;


import com.example.bb.domain.User;
import com.example.bb.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.bb.domain.Role.ROLE_USER;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;


@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUsernameTest() {
        Optional<User> user = userRepository.findByUsername("test");
        assertThat(user.isPresent());
    }

    @Test
    public void createNewUserTest() {
        User user = new User("test_user", ROLE_USER,"test_user@gmail.com", "$2y$12$0rRd4pqPE5hvGte36r3xgulgP40WwFsrqeXJcE8cmmm8y4lUwxfOW");
        userRepository.save(user);

        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void deleteUserTest() {
        Optional<User> user = userRepository.findByUsername("test");
        assertThat(user.isPresent());
        user.ifPresent(u -> {
            userRepository.deleteById(u.getId());
        });
        Optional<User> user2 = userRepository.findByUsername("test");
        assertThat(user2.isEmpty());
    }
}