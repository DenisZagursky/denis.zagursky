package com.netcracker.zagursky.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue
    private int id;
    private double price;

    public Price() {
    }

    public Price(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price1 = (Price) o;
        return id == price1.id &&
                Double.compare(price1.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Price{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
