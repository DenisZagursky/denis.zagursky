package com.netcracker.zagursky.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String description;
    @ElementCollection
    @CollectionTable(name = "tagForOrderItem", joinColumns = @JoinColumn(name = "orderitem_id"))
    @Column(name = "tags")
    private List<String> tags = new ArrayList<String>();
    private double price;
    private String category;


    public OrderItem() {
    }

    public OrderItem(String name, String description, double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (Double.compare(orderItem.price, price) != 0) return false;
        if (name != null ? !name.equals(orderItem.name) : orderItem.name != null) return false;
        if (description != null ? !description.equals(orderItem.description) : orderItem.description != null)
            return false;
        if (tags != null ? !tags.equals(orderItem.tags) : orderItem.tags != null) return false;
        return category != null ? category.equals(orderItem.category) : orderItem.category == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
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

    public List getTags() {
        return tags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }

}
