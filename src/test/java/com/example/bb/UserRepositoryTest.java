//package com.example.bb;
//
//import com.example.bb.domain._Role;
//import com.example.bb.domain.User;
//import com.example.bb.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.assertj.core.api.Assertions.*;
//
//import static com.example.bb.domain.ERole.ROLE_USER;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@SpringBootTest
//public class UserRepositoryTest {
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void findByUsernameTest() {
//        Optional<User> user = userRepository.findByUsername("test");
//        assertThat(user.isPresent());
//    }
//
//    @Test
//    public void createNewUserTest() {
//        User user = new User("test", "test@gmail.com", "$2y$12$0rRd4pqPE5hvGte36r3xgulgP40WwFsrqeXJcE8cmmm8y4lUwxfOW");
//
//        Set<_Role> userRoles = new HashSet<>();
//        userRoles.add(new _Role(ROLE_USER));
//        user.setRoles(userRoles);
//        userRepository.save(user);
//
//        assertThat(user.getId()).isNotNull();
//    }
//
//    @Test
//    public void deleteUserTest() {
//        Optional<User> user = userRepository.findByUsername("user");
//        assertThat(user.isPresent());
//        user.ifPresent(u -> {
//            userRepository.deleteById(u.getId());
//        });
//        Optional<User> user2 = userRepository.findByUsername("user");
//        assertThat(user2.isEmpty());
//    }
//}