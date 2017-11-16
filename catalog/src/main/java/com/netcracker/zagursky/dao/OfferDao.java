package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Offer;

import java.util.List;

/**
 * Created by Dzenyaa on 08.11.2017.
 */
public interface OfferDao extends GenericDao<Offer, Integer> {
    Offer findByName(String name) throws Exception;

    List<Offer> findByTag(String tagName) throws Exception;

    List<Offer> findByCategory(String categoryName) throws Exception;
}
