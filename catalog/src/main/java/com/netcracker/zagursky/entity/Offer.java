package com.netcracker.zagursky.entity;

import java.util.ArrayList;
import java.util.List;

public class Offer {
    private int id;
    private String name;
    private String description;
    private Price price;
    private List tags = new ArrayList<Tag>();
    private Category category;

    public Offer() {

    }

    public Offer(int id, String name, String description, double valuePrice, String nameCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        price = new Price(valuePrice);
        category = new Category(nameCategory);
    }

    public void addTag(String tagName) {
        tags.add(new Tag(tagName));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        if (id != offer.id) return false;
        if (name != null ? !name.equals(offer.name) : offer.name != null) return false;
        if (description != null ? !description.equals(offer.description) : offer.description != null) return false;
        if (price != null ? !price.equals(offer.price) : offer.price != null) return false;
        if (tags != null ? !tags.equals(offer.tags) : offer.tags != null) return false;
        return category != null ? category.equals(offer.category) : offer.category == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    public boolean removeTag(Tag tag) {
        return tags.remove(tag);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                ", category=" + category +
                '}';
    }
}
