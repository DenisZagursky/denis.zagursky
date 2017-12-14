package com.netcracker.zagursky.service.impl;

import com.netcracker.zagursky.client.CatalogClient;
import com.netcracker.zagursky.entity.catalog.Category;
import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.catalog.Tag;
import com.netcracker.zagursky.exception.ClientException;
import com.netcracker.zagursky.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dzenyaa on 11.12.2017.
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogClient catalogClient;

    @Override
    public List<Offer> getOffersByTags(List<String> tags) throws ClientException {
        StringBuilder builder = new StringBuilder();
        for (String tag : tags) {
            builder.append(tag).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        return catalogClient.getOffersByTags(builder.toString());
    }

    @Override
    public List<Offer> getOffersByCategory(String categoryName) throws ClientException {
        return catalogClient.getOffersByCategory(categoryName);
    }

    @Override
    public List<Offer> getOffersInPriceRange(Double belowPrice, double uponPrice) throws ClientException {
        return catalogClient.getOffersInPriceRange(belowPrice, uponPrice);
    }

    @Override
    public List<Category> getCategories() throws ClientException {
        return catalogClient.getCategories();
    }

    @Override
    public List<Tag> getTags() throws ClientException {
        return catalogClient.getTags();
    }
}
