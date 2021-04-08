package com.example.bb.ControllerTests;

import com.example.bb.domain.Item;
import com.example.bb.domain.ItemState;
import com.example.bb.domain.ItemType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

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
    public void updateItemTest() {

    }

    // get item by id
    @Test
    public void getItemByIdTest() {

    }

    // delete item by id
    @Test
    public void deleteItemTest() {

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
