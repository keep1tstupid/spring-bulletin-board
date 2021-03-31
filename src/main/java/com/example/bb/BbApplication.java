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

    public static void main(String[] args) {
        SpringApplication.run(BbApplication.class, args);
    }

    @Bean
    public CommandLineRunner bbDemo(ItemRepository itemRepository,
                                    UserRepository userRepository
                                    //RoleRepository roleRepository
    ) {
        return (args) -> { };
    }
}
