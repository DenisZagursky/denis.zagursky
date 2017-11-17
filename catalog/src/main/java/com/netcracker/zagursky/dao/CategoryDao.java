package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.exceptions.DbException;

public interface CategoryDao extends GenericDao<Category, Integer> {
    Category findByName(String categoryName) throws DbException;
}
