package com.netcracker.zagursky.dao.impl;

import com.netcracker.zagursky.dao.OfferDao;
import com.netcracker.zagursky.entity.Offer;

import java.util.List;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
public class OfferDaoImpl extends GenericDaoImpl<Offer, Integer> implements OfferDao {
    {
        setClass(Offer.class);
    }

    public Offer findByName(String name) {
        return (Offer) entityManager.createQuery(
                "SELECT c FROM Offer c WHERE c.name LIKE :custName")
                .setParameter("custName", name)
                .setMaxResults(1)
                .getSingleResult();

    }

    public List<Offer> findByTag(String tagName) {
        return entityManager.createQuery(
                "SELECT c FROM Offer c join c.tags p WHERE p.name LIKE :custName")
                .setParameter("custName", tagName)
                .getResultList();
    }

    public List<Offer> findByCategory(String categoryName) {
        return entityManager.createQuery(
                "SELECT c FROM Offer c join c.category p WHERE p.name LIKE :custName")
                .setParameter("custName", categoryName)
                .getResultList();
    }
}
