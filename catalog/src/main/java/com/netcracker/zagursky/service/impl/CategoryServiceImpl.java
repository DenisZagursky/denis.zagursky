package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.CategoryDao;
import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.DbException;
import com.netcracker.zagursky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
@Transactional
@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Integer> implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Transactional(readOnly = true)
    @Override
    public Category findByName(String categoryName) throws DbException {
        return categoryDao.findByName(categoryName);
    }

    @Override
    public List<Offer> getOffers(Integer id) throws DbException {
        return categoryDao.getOffers(id);
    }
}
