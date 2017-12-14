package com.netcracker.zagursky.entity.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Dzenyaa on 11.12.2017.
 */
public class Price {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("price")
    private Double price;

    public Price() {
    }

    public Price(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
