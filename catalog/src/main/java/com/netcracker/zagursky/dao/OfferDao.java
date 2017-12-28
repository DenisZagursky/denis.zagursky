package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.entity.OffersFilter;
import com.netcracker.zagursky.exceptions.CatalogException;

import java.util.List;

/**
 * Created by Dzenyaa on 08.11.2017.
 */
public interface OfferDao extends GenericDao<Offer, Integer> {
    Offer findByName(String name) throws CatalogException;

    List<Offer> findByTag(String tagName) throws CatalogException;

    List<Offer> findByCategory(String categoryName) throws CatalogException;

    List<Offer> getAvailableOffers() throws CatalogException;

    List<Offer> findOffersInRange(double belowPrice, double uponPrice) throws CatalogException;


    List<Offer> findOffersByFilter(OffersFilter filter, String query) throws CatalogException;
}
