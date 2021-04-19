package com.example.bb;

import com.example.bb.repository.ItemRepository;
import com.example.bb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BbApplication {
    public static void main(String[] args) {
        SpringApplication.run(BbApplication.class, args);
    }
}