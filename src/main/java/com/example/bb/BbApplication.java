package com.example.bb;

import com.example.bb.domain.Item;
import com.example.bb.domain.ItemState;
import com.example.bb.domain.ItemType;
import com.example.bb.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbApplication.class, args);
    }

    @Bean
    public CommandLineRunner bbDemo(ItemRepository itemRepository) {
        return (args) -> {
            itemRepository.save(
                    new Item("title1", ItemType.NOTE, ItemState.APPROVED,
                            "descr1", "contact1"));
            itemRepository.save(
                    new Item("title2", ItemType.COMPLAINT, ItemState.APPROVED,
                            "lorem ipsum", "contact2"));
            itemRepository.save(
                    new Item("title3", ItemType.ADVERTISEMENT, ItemState.IN_MODERATION,
                            "qwertqwerty", "123123123"));
        };
    }

}
