package com.netcracker.zagursky.client;

import com.netcracker.zagursky.entity.catalog.Category;
import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.catalog.Tag;
import com.netcracker.zagursky.exception.ClientException;

import java.util.List;

/**
 * Created by Dzenyaa on 11.12.2017.
 */
public interface CatalogClient {
    List<Offer> getOffersByTags(String tags) throws ClientException;
    List<Offer> getOffersByCategory(String categoryName) throws ClientException;
    List<Offer> getOffersInPriceRange(Double belowPrice,double uponPrice) throws ClientException;
    List<Category> getCategories() throws ClientException;
    List<Tag> getTags() throws ClientException;
}
