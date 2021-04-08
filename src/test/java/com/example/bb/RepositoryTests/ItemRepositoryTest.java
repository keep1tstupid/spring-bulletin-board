package com.example.bb.RepositoryTests;

import com.example.bb.domain.Item;
import com.example.bb.domain.ItemState;
import com.example.bb.domain.ItemType;
import com.example.bb.domain.User;
import com.example.bb.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void findByTypeTest() {
        List<Item> items = itemRepository.findByType(ItemType.ADVERTISEMENT);
        assertThat(items.size() != 0);
    }

    @Test
    public void addNewItemTest() {
        itemRepository.save(
                    new Item("testAuthor", "Test Title", ItemType.NOTE, ItemState.APPROVED,
                            "description", "012-345-6789"));
        Optional<Item> savedItem = itemRepository.findByTitle("Test Title");
        assertThat(savedItem.isPresent());
    }

    @Test
    public void deleteItemTest() {
        itemRepository.save(
                new Item("testAuthor", "Test", ItemType.COMPLAINT, ItemState.APPROVED,
                        "description", "012-345-6789"));
        Optional<Item> item = itemRepository.findByTitle("Test");
        assertThat(item.isPresent());
        item.ifPresent(i -> {
            itemRepository.deleteById(i.getId());
        });
        Optional<Item> deletedItem = itemRepository.findByTitle("Test");
        assertThat(!deletedItem.isPresent());
    }
}
