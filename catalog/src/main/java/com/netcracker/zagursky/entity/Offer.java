package com.netcracker.zagursky.entity;

import java.util.ArrayList;
import java.util.List;

public class Offer {
    private int id;
    private String name;
    private String description;
    private Price price = new Price();
    private List<Tag> tags = new ArrayList<Tag>();
    private Category category;

    public Offer(int id, String name, String description, double price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price.setPrice(price);
        this.category = category;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public boolean removeTag(Tag tag) {
        return tags.remove(tag);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Category getCategory() {
        return category;
    }
}
