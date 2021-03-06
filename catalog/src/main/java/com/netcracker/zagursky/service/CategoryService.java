package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.CatalogException;

import java.util.List;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
public interface CategoryService extends GenericService<Category, Integer> {
    Category findByName(String categoryName) throws CatalogException;

    List<Offer> getOffers(Integer id) throws CatalogException;
}
