package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.CatalogException;

import java.util.List;

public interface CategoryDao extends GenericDao<Category, Integer> {
    Category findByName(String categoryName) throws CatalogException;

    List<Offer> getOffers(Integer id) throws CatalogException;
}
