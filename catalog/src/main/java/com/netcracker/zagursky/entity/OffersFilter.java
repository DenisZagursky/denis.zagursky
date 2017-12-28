package com.netcracker.zagursky.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Dzenyaa on 24.12.2017.
 */
public class OffersFilter {
    private List tags = new ArrayList<String>();
    private double belowPrice;
    private double uponPrice;
    private String categoryName;

    public OffersFilter() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffersFilter)) return false;
        OffersFilter that = (OffersFilter) o;
        return Double.compare(that.belowPrice, belowPrice) == 0 &&
                Double.compare(that.uponPrice, uponPrice) == 0 &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(belowPrice, uponPrice, categoryName, tags);
    }

    public double getBelowPrice() {
        return belowPrice;
    }

    public void setBelowPrice(double belowPrice) {
        this.belowPrice = belowPrice;
    }

    public double getUponPrice() {
        return uponPrice;
    }

    public void setUponPrice(double uponPrice) {
        this.uponPrice = uponPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List getTags() {
        return tags;
    }

    public void setTags(List tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "OffersFilter{" +
                "belowPrice=" + belowPrice +
                ", uponPrice=" + uponPrice +
                ", categoryName='" + categoryName + '\'' +
                ", tags=" + tags +
                '}';
    }
}
