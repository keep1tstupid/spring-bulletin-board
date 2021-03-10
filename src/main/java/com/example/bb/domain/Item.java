package com.example.bb.domain;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemState state;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "contactInfo", nullable = false)
    private String contactInfo;

    public Item() {}

    public Item(String title, ItemType type, ItemState state, String description, String contactInfo) {
        this.title = title;
        this.type = type;
        this.state = state;
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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public ItemState getState() {
        return state;
    }

    public void setState(ItemState state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
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
