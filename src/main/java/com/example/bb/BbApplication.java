package com.example.bb;

import com.example.bb.domain.*;
import com.example.bb.repository.ItemRepository;
//import com.example.bb.repository.RoleRepository;
import com.example.bb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.example.bb.domain.Role.*;


@SpringBootApplication
public class BbApplication {

    // todo : add tests!!!

    public static void main(String[] args) {
        SpringApplication.run(BbApplication.class, args);
    }

    @Bean
    public CommandLineRunner bbDemo(ItemRepository itemRepository,
                                    UserRepository userRepository
                                    //RoleRepository roleRepository
    ) {
        return (args) -> {
            itemRepository.save(
                    new Item("leena", "Note", ItemType.NOTE, ItemState.APPROVED,
                            "description", "contact1"));
            itemRepository.save(
                    new Item("test", "I tired!", ItemType.COMPLAINT, ItemState.APPROVED,
                            "lorem ipsum", "contact2"));
            itemRepository.save(
                    new Item("rabjana", "One more bike for sale", ItemType.ADVERTISEMENT, ItemState.APPROVED,
                            "Selling almost new bike", "call me 123123123"));

            User user1 = new User("adm", ROLE_ADMIN ,"adm@gmail.com", "$2y$12$0rRd4pqPE5hvGte36r3xgulgP40WwFsrqeXJcE8cmmm8y4lUwxfOW");
            userRepository.save(user1);

            User user2 = new User("user", ROLE_USER,"user@gmail.com", "$2y$12$0rRd4pqPE5hvGte36r3xgulgP40WwFsrqeXJcE8cmmm8y4lUwxfOW");
            userRepository.save(user2);

            User user3 = new User("mod", ROLE_MODERATOR, "adm@gmail.com", "$2y$12$0rRd4pqPE5hvGte36r3xgulgP40WwFsrqeXJcE8cmmm8y4lUwxfOW");
            userRepository.save(user3);

            User user4 = new User("test", ROLE_MODERATOR, "test@gmail.com", "$2y$12$0rRd4pqPE5hvGte36r3xgulgP40WwFsrqeXJcE8cmmm8y4lUwxfOW");
            userRepository.save(user4);
        };
    }
}
