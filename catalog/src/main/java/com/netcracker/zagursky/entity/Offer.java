package com.netcracker.zagursky.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Price price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "offertag", joinColumns = {
            @JoinColumn(name = "offer_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false, updatable = false)

            })
    private List<Tag> tags = new ArrayList<Tag>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public Offer() {

    }

    public Offer(String name, String description, double valuePrice, String nameCategory) {
        this.name = name;
        this.description = description;
        price = new Price(valuePrice);
        category = new Category(nameCategory);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
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
