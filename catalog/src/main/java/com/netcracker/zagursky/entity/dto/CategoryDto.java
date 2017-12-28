package com.netcracker.zagursky.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.zagursky.entity.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzenyaa on 07.12.2017.
 */
public class CategoryDto {
    @JsonProperty("id")
    private int id;
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

    public static List<Category> getCategoryEntity(List<CategoryDto> categoryDtoList) {
        List<Category> categories = new ArrayList<>();
        for (CategoryDto categoryDto : categoryDtoList) {
            categories.add(CategoryDto.getCategoryEntity(categoryDto));
        }
        return categories;
    }

    public static Category getCategoryEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
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
