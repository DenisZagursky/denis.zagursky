package com.netcracker.zagursky.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.zagursky.entity.Price;

/**
 * Created by Dzenyaa on 11.12.2017.
 */
public class PriceDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("price")
    private double price;

    public static PriceDto fromModel(Price price) {
        PriceDto dto = new PriceDto();
        dto.setId(price.getId());
        dto.setPrice(price.getPrice());
        return dto;
    }

    public static Price getPriceEntity(PriceDto priceDto) {
        Price price = new Price();
        price.setId(priceDto.getId());
        price.setPrice(priceDto.getPrice());
        return price;
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
