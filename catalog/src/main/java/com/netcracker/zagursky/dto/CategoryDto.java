package com.netcracker.zagursky.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.zagursky.entity.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzenyaa on 07.12.2017.
 */
public class CategoryDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;

    public static CategoryDto fromModel(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public static List<CategoryDto> fromModel(List<Category> categories) {
        List<CategoryDto> dto = new ArrayList<>();
        for (Category category : categories) {
            dto.add(CategoryDto.fromModel(category));
        }
        return dto;
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
