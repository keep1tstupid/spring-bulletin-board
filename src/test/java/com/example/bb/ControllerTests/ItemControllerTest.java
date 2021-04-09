package com.example.bb.ControllerTests;

import com.example.bb.domain.Item;
import com.example.bb.domain.ItemState;
import com.example.bb.domain.ItemType;
import com.example.bb.repository.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // add new item
    @Test
    public void createNewItemTest() throws Exception {
        Item item = new Item("testAuthor2", "Test Title 2", ItemType.NOTE, ItemState.APPROVED,
                "test description", "012-345-6789-111");
        mockMvc.perform(post("/api/items")
                .contentType("application/json").content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk());
    }

    // update item
    @Test
    public void updateItemTest() throws Exception {
        Item item = new Item("testItemController", "testItemController updateItemTest", ItemType.ADVERTISEMENT,
                ItemState.APPROVED, "test description", "012-345");
        Item savedItem = itemRepository.save(item);
        Item updItem = new Item("UPDATED", "UPDATED", ItemType.ADVERTISEMENT,
                ItemState.APPROVED, "UPDATED", "UPDATED");
        String url = "/api/items/" + savedItem.getId();
        mockMvc.perform(put(url).content(objectMapper.writeValueAsString(updItem))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // get item by id
    @Test
    public void getItemByIdTest() throws Exception {
        Item item = new Item("testItemController", "testItemController getItemByIdTest", ItemType.NOTE,
                ItemState.APPROVED, "test description", "012-345");
        Item savedItem = itemRepository.save(item);
        String url = "/api/items/" + savedItem.getId();
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    // delete item by id
    @Test
    public void deleteItemTest() throws Exception {
        Item item = new Item("testItemController", "testItemController deleteItemTest", ItemType.NOTE, ItemState.APPROVED,
                "test description", "012-345");
        Item savedItem = itemRepository.save(item);
        String url = "/api/items/" + savedItem.getId();
        mockMvc.perform(delete(url)).andExpect(status().isOk());
    }

    // get all items
    @Test
    public void getAllItemsTest() throws Exception {
        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk());
    }

    // get all item types
    @Test
    public void getAllItemTypes() throws Exception {
        mockMvc.perform(get("/api/types"))
                .andExpect(status().isOk());
    }
}