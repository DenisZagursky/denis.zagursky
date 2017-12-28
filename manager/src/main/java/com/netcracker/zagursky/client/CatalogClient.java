package com.netcracker.zagursky.client;

import com.netcracker.zagursky.entity.catalog.Category;
import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.catalog.OffersFilter;
import com.netcracker.zagursky.entity.catalog.Tag;
import com.netcracker.zagursky.exception.ManagerException;

import java.util.List;

/**
 * Created by Dzenyaa on 11.12.2017.
 */
public interface CatalogClient {
    List<Offer> getOffersByTags(String tags) throws ManagerException;

    List<Offer> getOffersByCategory(String categoryName) throws ManagerException;

    List<Offer> getOffersInPriceRange(Double belowPrice, double uponPrice) throws ManagerException;

    List<Category> getCategories() throws ManagerException;

    List<Tag> getTags() throws ManagerException;
    Offer getOfferById(Integer id) throws ManagerException;
    List<Offer> getOffersByFilter(OffersFilter filter) throws ManagerException;
}
