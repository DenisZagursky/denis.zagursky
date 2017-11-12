package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.entity.Offer;

import java.util.List;

/**
 * Created by Dzenyaa on 08.11.2017.
 */
public interface OfferDao extends GenericDao<Offer,Long> {
    public Offer findByName(String name);
    public List<Offer> findByTag(String tagName);
    public List<Offer> findByCategory(String categoryName);
}
