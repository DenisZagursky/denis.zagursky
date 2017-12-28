package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.client.CatalogClient;
import com.netcracker.zagursky.entity.catalog.Category;
import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.catalog.OffersFilter;
import com.netcracker.zagursky.entity.catalog.Tag;
import com.netcracker.zagursky.exception.ManagerException;
import com.netcracker.zagursky.service.CatalogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dzenyaa on 11.12.2017.
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    private static final Logger LOGGER = LogManager.getLogger("logger");

    @Autowired
    private CatalogClient catalogClient;

    @Override
    public List<Offer> getOffersByTags(List<String> tags) throws ManagerException {
        LOGGER.debug("Get Offers By Tags. Start transaction");
        StringBuilder builder = new StringBuilder();
        for (String tag : tags) {
            builder.append(tag).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        List<Offer> offers= catalogClient.getOffersByTags(builder.toString());
        LOGGER.debug("Get offers by tags. End transaction. offers:"+offers);
        return offers;

    }

    @Override
    public List<Offer> getOffersByCategory(String categoryName) throws ManagerException {
        LOGGER.debug("Get offers by tags. Start transaction");
        List<Offer> offers= catalogClient.getOffersByCategory(categoryName);
        LOGGER.debug("Get offers by tags. End transaction.Offers:"+offers);
        return offers;
    }

    @Override
    public List<Offer> getOffersInPriceRange(Double belowPrice, double uponPrice) throws ManagerException {
        LOGGER.debug("Get Offers By Tags. Start transaction");
        List<Offer> offers= catalogClient.getOffersInPriceRange(belowPrice, uponPrice);
        LOGGER.debug("Get offers by tags. End transaction. Offers:"+offers);
        return offers;

    }

    @Override
    public List<Category> getCategories() throws ManagerException {
        LOGGER.debug("Get Offers By Tags. Start transaction");
        List<Category>  categories=catalogClient.getCategories();
        LOGGER.debug("Get offers by tags. End transaction. Categories:"+categories);
        return categories;

    }

    @Override
    public List<Tag> getTags() throws ManagerException {
        LOGGER.debug("Get Offers By Tags. Start transaction");
        List<Tag> tags= catalogClient.getTags();
        LOGGER.debug("Get offers by tags. End transaction. Tags:"+tags);
        return tags;

    }

    @Override
    public List<Offer> getOffersByFilter(OffersFilter filter) throws ManagerException {
        LOGGER.debug("Get Offers By Tags. Start transaction");
        List<Offer> offers= catalogClient.getOffersByFilter(filter);
        LOGGER.debug("Get offers by tags. End transaction. Offers:"+offers);
        return offers;

    }

    @Override
    public Offer getOfferById(Integer id) throws ManagerException {
        LOGGER.debug("Get Offers By Tags. Start transaction");
        Offer offer= catalogClient.getOfferById(id);
        LOGGER.debug("Get offers by tags. End transaction. Offer:"+offer);
        return offer;

    }
}
