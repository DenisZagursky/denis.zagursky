package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.CategoryDao;
import com.netcracker.zagursky.entity.Category;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
public class CategoryDaoImpl extends GenericDaoImpl<Category, Integer> implements CategoryDao {
    {
        setClass(Category.class);
    }

    public Category findByName(String categoryName) {
        return (Category) entityManager.createQuery(
                "SELECT c FROM Category c WHERE c.name LIKE :custName")
                .setParameter("custName", categoryName)
                .setMaxResults(1)
                .getSingleResult();

    }
}
