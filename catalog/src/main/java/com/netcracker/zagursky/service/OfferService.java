package com.netcracker.zagursky.service;

import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.DbException;

import java.util.List;

/**
 * Created by Dzenyaa on 27.11.2017.
 */
public interface OfferService extends GenericService<Offer, Integer> {
    Offer findByName(String name) throws DbException;

    List<Offer> findByTag(String tagName) throws DbException;

    List<Offer> findByCategory(String categoryName) throws DbException;
}
