package com.example.bb.domain;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    private String title;
    private String type;
    private String description;
    private String contactInfo;

    public Item() {}

    public Item(String title, String type, String description, String contactInfo) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.contactInfo = contactInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
