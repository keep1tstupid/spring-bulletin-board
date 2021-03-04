package com.example.bb.domain;

public enum ItemType {
    ADVERTISEMENT("ad"),
    NOTE("note"),
    COMPLAINT("complaint"),
    OTHER("other");

    public final String name;

    ItemType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
