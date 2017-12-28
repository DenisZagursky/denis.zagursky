package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.CategoryDao;
import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.CatalogException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Integer> implements CategoryDao {
    public static final String QUERY_FIND_BY_NAME = "SELECT c FROM Category c WHERE c.name LIKE :custName";
    private static final String QUERY_GET_OFFERS = "select c from Offer c  where c.category.id=:custName";

    {
        setClass(Category.class);
    }


    public Category findByName(String categoryName) throws CatalogException {
        try {
            return (Category) entityManager.createQuery(QUERY_FIND_BY_NAME)
                    .setParameter("custName", categoryName)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }
    }

    @Override
    public List<Offer> getOffers(Integer id) throws CatalogException {
        try {
            return entityManager.createQuery(QUERY_GET_OFFERS)
                    .setParameter("custName", id)
                    .getResultList();
        } catch (Exception ex) {
            throw new CatalogException("not valid arguments", ex);
        }
    }
}
