package com.example.bb.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attachments")
public class Attachment {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    // large object data
    @Lob
    private byte[] data;

    private String type;

    private String name;

    private String url;

    private Date uploadingTime = new Date();

    public Attachment(String name, String type, byte[] data, String url) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.url = url;
    }

    // constructor for test
    public Attachment(String name) {
        this.name = name;
    }

    public Attachment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}