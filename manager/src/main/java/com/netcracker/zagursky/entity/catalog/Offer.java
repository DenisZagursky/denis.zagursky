package com.netcracker.zagursky.entity.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Dzenyaa on 07.12.2017.
 */
public class Offer {
    @JsonProperty("id")
    private int id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private boolean status;
    @JsonProperty("tags")
    private List<Tag> tags;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("price")
    private Price price;

    public Offer() {
    }

    public Offer(String description, String name, boolean status) {
        this.description = description;
        this.name = name;
        this.status = status;
    }


    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
