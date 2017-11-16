package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.OfferDao;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.exceptions.BdException;

import java.util.List;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
public class OfferDaoImpl extends GenericDaoImpl<Offer, Integer> implements OfferDao {
    public static final String QUERY_FIND_BY_NAME = "SELECT c FROM Offer c WHERE c.name LIKE :custName";
    public static final String QUERY_FIND_BY_TAG = "SELECT c FROM Offer c join c.tags p WHERE p.name LIKE :custName";
    public static final String QUERY_FIND_BY_CATEGORY = "SELECT c FROM Offer c join c.category p WHERE p.name LIKE :custName";

    {
        setClass(Offer.class);
    }

    public Offer findByName(String name) throws Exception {
        try {
            return (Offer) entityManager.createQuery(QUERY_FIND_BY_NAME)
                    .setParameter("custName", name)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (IllegalArgumentException ex) {
            throw new BdException("not valid arguments", ex);
        }

    }

    public List<Offer> findByTag(String tagName) throws Exception {
        try {
            return entityManager.createQuery(QUERY_FIND_BY_TAG)
                    .setParameter("custName", tagName)
                    .getResultList();
        } catch (IllegalArgumentException ex) {
            throw new BdException("not valid arguments", ex);
        }
    }

    public List<Offer> findByCategory(String categoryName) throws Exception {
        try {
            return entityManager.createQuery(QUERY_FIND_BY_CATEGORY)
                    .setParameter("custName", categoryName)
                    .getResultList();
        } catch (IllegalArgumentException ex) {
            throw new BdException("not valid arguments", ex);
        }
    }
}
