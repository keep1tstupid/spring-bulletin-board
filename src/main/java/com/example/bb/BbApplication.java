package com.example.bb;

import com.example.bb.domain.*;
import com.example.bb.repository.ItemRepository;
import com.example.bb.repository.RoleRepository;
import com.example.bb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

import static com.example.bb.domain.ERole.ROLE_ADMIN;
import static com.example.bb.domain.ERole.ROLE_USER;

@SpringBootApplication
public class BbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbApplication.class, args);
    }

    @Bean
    public CommandLineRunner bbDemo(ItemRepository itemRepository,
                                    UserRepository userRepository,
                                    RoleRepository roleRepository) {
        return (args) -> {
//            itemRepository.save(
//                    new Item("leena", "Note my note", ItemType.NOTE, ItemState.APPROVED,
//                            "descr1", "contact1"));
//            itemRepository.save(
//                    new Item("test", "I tired!", ItemType.ADVERTISEMENT, ItemState.APPROVED,
//                            "lorem ipsum", "contact2"));
//            itemRepository.save(
//                    new Item("rabjana", "Bike for sale", ItemType.ADVERTISEMENT, ItemState.IN_MODERATION,
//                            "Selling almost new bike", "call me 123123123"));
//
//            User user = new User("test", "lolka@gmail.com", "$2y$12$0rRd4pqPE5hvGte36r3xgulgP40WwFsrqeXJcE8cmmm8y4lUwxfOW");
//            Set<Role> userRoles = new HashSet<>();
//            userRoles.add(new Role(ROLE_USER));
//            userRoles.add(new Role(ROLE_ADMIN));
//            user.setRoles(userRoles);
//
//            userRepository.save(user);
        };
    }
}
