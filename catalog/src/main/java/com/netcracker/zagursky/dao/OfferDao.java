package com.netcracker.zagursky.dao;

import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.DbException;

import java.util.List;

/**
 * Created by Dzenyaa on 08.11.2017.
 */
public interface OfferDao extends GenericDao<Offer, Integer> {
    Offer findByName(String name) throws DbException;

    List<Offer> findByTag(String tagName) throws DbException;

    List<Offer> findByCategory(String categoryName) throws DbException;
}
