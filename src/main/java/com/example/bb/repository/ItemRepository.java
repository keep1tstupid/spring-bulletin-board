package com.example.bb.repository;

import com.example.bb.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByType(String type);
}
