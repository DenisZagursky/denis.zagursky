package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.dao.CategoryDao;
import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.CatalogException;
import com.netcracker.zagursky.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private final static Logger LOGGER = LogManager.getLogger("logger");

    @Autowired
    private CategoryDao categoryDao;

    @Transactional(readOnly = true)
    @Override
    public Category findByName(String categoryName) throws CatalogException {
        LOGGER.debug("Find by Name. Start transaction");
        Category category= categoryDao.findByName(categoryName);
        LOGGER.debug("Find by Name. End transaction. category:"+category);
        return category;
    }

    @Override
    public List<Offer> getOffers(Integer id) throws CatalogException {
        LOGGER.debug("Get offers. Start transaction.");
        List<Offer> offers= categoryDao.getOffers(id);
        LOGGER.debug("Get offers. End transaction. offers:"+offers);
        return offers;
    }
}
