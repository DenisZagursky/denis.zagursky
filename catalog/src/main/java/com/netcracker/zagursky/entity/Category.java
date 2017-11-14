package com.netcracker.zagursky.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToOne(optional = true)
    private Offer offers;

    public Category() {
    }

    public Category(String category) {
        name = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(name, category.name) &&
                Objects.equals(offers, category.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, offers);
    }

    public String getName() {
        return name;
    }

    public void setName(String category) {
        name = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", offers=").append(offers);
        sb.append('}');
        return sb.toString();
    }

    public Offer getOffers() {
        return offers;
    }

    public void setOffers(Offer offers) {
        this.offers = offers;
    }
}
