package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Category;

public interface CategoryDao extends GenericDao<Category, Integer> {
    Category findByName(String categoryName);
}
