package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.CategoryDao;
import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.exceptions.DbException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
@Repository
@Transactional(rollbackOn = DbException.class)
public class CategoryDaoImpl extends GenericDaoImpl<Category, Integer> implements CategoryDao {
    public static final String QUERY_FIND_BY_NAME = "SELECT c FROM Category c WHERE c.name LIKE :custName";

    {
        setClass(Category.class);
    }


    public Category findByName(String categoryName) throws DbException {
        try {
            return (Category) entityManager.createQuery(QUERY_FIND_BY_NAME)
                    .setParameter("custName", categoryName)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new DbException("not valid arguments", ex);
        }
    }
}
