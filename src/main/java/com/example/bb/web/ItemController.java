package com.example.bb.web;

import com.example.bb.domain.Item;
import com.example.bb.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/home")
    public String showAllItems(Model model) {
        List<Item> items = (List<Item>) itemRepository.findAll();

        model.addAttribute("items", items);

        return "itemsList";
    }
}
