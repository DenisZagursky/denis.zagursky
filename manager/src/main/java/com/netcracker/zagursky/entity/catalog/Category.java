package com.netcracker.zagursky.entity.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Dzenyaa on 07.12.2017.
 */
public class Category {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
