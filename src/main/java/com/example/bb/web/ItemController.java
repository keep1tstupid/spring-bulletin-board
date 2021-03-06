package com.example.bb.web;

import com.example.bb.domain.Item;
import com.example.bb.domain.ItemType;
import com.example.bb.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
// @CrossOrigin("*")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    // get all items
    @GetMapping("/items")
    public @ResponseBody
    List<Item> allItems() {
        return (List<Item>) itemRepository.findAll();
    }

    // get all item types
    @GetMapping("/types")
    public @ResponseBody
    List<ItemType> allItemTypes() {
        ItemType[] itemTypes = ItemType.values();
        return Arrays.asList(itemTypes);
    }

    // add new item
    @PostMapping("/items")
    Item addItem(@RequestBody Item newItem) {
        return itemRepository.save(newItem);
    }

    // update item
    @PutMapping("/items/{id}")
    Optional<Item> updateItem(@RequestBody Item newItem, @PathVariable("id") Long itemId) {
        return itemRepository.findById(itemId)
                .map(item -> {
                    item.setTitle(newItem.getTitle());
                    item.setType(newItem.getType());
                    item.setDescription(newItem.getDescription());
                    item.setContactInfo(newItem.getContactInfo());
                    item.setState(newItem.getState());
                    return itemRepository.save(item);
                });
    }

    // get item by id
    @GetMapping("/items/{id}")
    public @ResponseBody
    Optional<Item> findItem(@PathVariable("id") Long itemId) {
        return itemRepository.findById(itemId);
    }

    // delete item by id
    @DeleteMapping("/items/{id}")
    void deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }
}