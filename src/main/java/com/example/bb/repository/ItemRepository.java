package com.example.bb.repository;

import com.example.bb.domain.Item;
import com.example.bb.domain.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByType(ItemType type);
    Optional<Item> findByTitle(String title);
}
