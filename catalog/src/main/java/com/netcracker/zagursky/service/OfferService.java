package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.*;
import com.netcracker.zagursky.exceptions.CatalogException;

import java.util.List;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
public interface OfferService extends GenericService<Offer, Integer> {
    Offer findByName(String name) throws CatalogException;

    List<Offer> findByTags(List<String> tags) throws CatalogException;

    List<Offer> findByCategory(String categoryName) throws CatalogException;

    void changeStatus(Offer offer) throws CatalogException;

    List<Offer> getAvailableOffers() throws CatalogException;

    Offer addTag(int id, Tag tag) throws CatalogException;

    Offer removeTag(Integer id, Tag tag) throws CatalogException;

    Offer setCategory(Integer id, Category category) throws CatalogException;

    Offer removeCategory(Integer id) throws CatalogException;

    Offer setPrice(Integer id, Price price) throws CatalogException;

    Offer changePrice(Integer id, Integer price) throws CatalogException;

    List<Offer> findByPrice(double belowPrice, double uponPrice) throws CatalogException;

    List<Offer> findByFilter(OffersFilter filter) throws CatalogException;

}
